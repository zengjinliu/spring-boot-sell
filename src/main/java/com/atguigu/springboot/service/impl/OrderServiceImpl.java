package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.OrderDetail;
import com.atguigu.springboot.bean.OrderMaster;
import com.atguigu.springboot.bean.ProductInfo;
import com.atguigu.springboot.converter.OrderMaster2OrderDTO;
import com.atguigu.springboot.dao.OrderDetailRepository;
import com.atguigu.springboot.dao.OrderMasterRepository;
import com.atguigu.springboot.dto.CartDTO;
import com.atguigu.springboot.dto.OrderDTO;
import com.atguigu.springboot.enums.OrderStatusEnum;
import com.atguigu.springboot.enums.PayStatusEnum;
import com.atguigu.springboot.enums.ResultEnum;
import com.atguigu.springboot.exception.SellException;
import com.atguigu.springboot.service.serviceInterface.OrderService;
import com.atguigu.springboot.service.serviceInterface.ProductService;
import com.atguigu.springboot.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 15:42
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	ProductService productService;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	OrderMasterRepository orderMasterRepository;
	@Autowired
	WebSocket webSocket;

	/**
	 * 创建订单
	 *
	 * @param orderDTO
	 * @return
	 */
	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

		String orderId = KeyUtil.genUniqueKey();

		//1.查询商品（数量，价格）
		ProductInfo productInfo = null;
		List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
		for (OrderDetail orderDetail : orderDetailList) {
			productInfo = productService.findOne(orderDetail.getProductId());
			if (productInfo == null) {
				//抛出自定义的异常
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			//2.计算订单总价
			orderAmount = productInfo.getProductPrice()
					.multiply(new BigDecimal(orderDetail.getProductQuantity()))
					.add(orderAmount);

			//创建随机数的方法
			orderDetail.setOrderId(orderId);
			orderDetail.setDetailId(KeyUtil.genUniqueKey());
			//将商品的属性拷贝过来
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetailRepository.save(orderDetail);
		}
		//3.写入订单数据总库
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
		orderMasterRepository.save(orderMaster);
		//4.库存减少 lambda表达式
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
				new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		productService.decreaseStock(cartDTOList);
		webSocket.sendMessage(orderDTO.getOrderId());
		return orderDTO;
	}

	/**
	 * 查询单个订单
	 *
	 * @param orderId
	 * @return
	 */
	@Override
	public OrderDTO findOne(String orderId) {
		OrderMaster orderMaster = null;

		Optional<OrderMaster> optionalOrderMaster = orderMasterRepository.findById(orderId);
		if (optionalOrderMaster!=null && optionalOrderMaster.isPresent()){
			 orderMaster = optionalOrderMaster.get();
		}
		if (orderMaster == null) {
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}

		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
		if (orderDetailList == null) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}

	/**
	 * 查询订单列表
	 *
	 * @param buyerOpenId
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
		List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
		return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
	}

	/**
	 * 卖家端查询所有订单
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<OrderDTO> list(Pageable pageable) {
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
		List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
		return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
	}

	/**
	 * 取消订单
	 *
	 * @param orderDTO
	 * @return
	 */
	@Override
	@Transactional
	public OrderDTO cancel(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();

		//判断订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("【取消订单】 订单状态不正确 orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster result = orderMasterRepository.save(orderMaster);
		if (result == null) {
			log.info("【取消订单】 更新失败 orderMaster={}", orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}


		//返还库存
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.info("【取消订单】 订单中无商品 orderDTO={}", orderDTO);
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
				.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
				.collect(Collectors.toList());
		productService.increaseStock(cartDTOList);

		//如果已支付需要退款
		if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
			//TODO
		}
		return orderDTO;
	}

	/**
	 * 完结订单
	 *
	 * @param orderDTO
	 * @return
	 */
	@Override
	@Transactional
	public OrderDTO finish(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		//判断订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("【取消订单】 订单状态不正确 orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}

		//修改状态
		orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster result = orderMasterRepository.save(orderMaster);
		if (result == null) {
			log.error("【完结订单】 订单状态不正确 orderId={},orderStatus", orderMaster.getOrderId(), orderMaster.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		return orderDTO;
	}

	/**
	 * 支付订单
	 * @param orderDTO
	 * @return
	 */
	@Override
	@Transactional
	public OrderDTO paid(OrderDTO orderDTO) {
		//判断订单状态
		OrderMaster orderMaster = new OrderMaster();
		//判断订单状态
		if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			log.error("【取消订单】 订单状态不正确 orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		//判断支付状态
		if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
			log.error("【订单支付完成】 订单支付状态不正确 orderId={},payStatus={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}

		//修改支付状态
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster result = orderMasterRepository.save(orderMaster);
		if (result == null) {
			log.error("【订单支付完成】 跟新失败 orderId={},orderStatus", orderMaster.getOrderId(), orderMaster.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}
}

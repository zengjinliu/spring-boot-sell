package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.dto.OrderDTO;
import com.atguigu.springboot.enums.ResultEnum;
import com.atguigu.springboot.exception.SellException;
import com.atguigu.springboot.service.serviceInterface.BuyerService;
import com.atguigu.springboot.service.serviceInterface.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/27 8:29
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private OrderService orderService;

	/**
	 * 查询单个订单
	 * @param openId
	 * @param orderId
	 * @return
	 */
	@Override
	public OrderDTO findOrderOne(String openId, String orderId) {
		OrderDTO orderDTO = orderService.findOne(orderId);
		if (orderDTO==null){
			return null;
		}
		if (!openId.equalsIgnoreCase(orderDTO.getBuyerOpenid())){
			log.error("【查询订单】 订单的openId不一致 orderDTO={}", orderDTO);
			throw new SellException(ResultEnum.OPENID_OWNER_ERROR);
		}
		return orderDTO;
	}

	@Override
	public OrderDTO cancelOrder(String openId, String orderId) {
		OrderDTO orderDTO = orderService.findOne(orderId);
		if (orderDTO==null){
			log.error("【查询订单】 查不到该订单, orderid={}", orderId);
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		return orderService.cancel(orderDTO);
	}
}











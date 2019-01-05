package com.atguigu.springboot.controller;

import com.alibaba.druid.sql.PagerUtils;
import com.atguigu.springboot.VO.ResultVO;
import com.atguigu.springboot.converter.OrderForm2OrderDTO;
import com.atguigu.springboot.dto.OrderDTO;
import com.atguigu.springboot.enums.ResultEnum;
import com.atguigu.springboot.exception.SellException;
import com.atguigu.springboot.form.OrderForm;
import com.atguigu.springboot.service.serviceInterface.BuyerService;
import com.atguigu.springboot.service.serviceInterface.OrderService;
import com.atguigu.springboot.util.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/26 15:47
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	BuyerService buyerService;


	//创建订单,表单校验
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
												BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.error("【创建订单】 参数不正确, orderForm={}", orderForm);
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.error("【创建订单】 购物车不能为空");
			throw new SellException(ResultEnum.CART_EMPTY);
		}
		OrderDTO result = orderService.create(orderDTO);
		Map<String, String> map = new HashMap<>();
		map.put("orderId", result.getOrderId());
		return ResultVoUtil.success(map);
	}

	//订单列表
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId,
										 @RequestParam(value = "page", defaultValue = "0") Integer page,
										 @RequestParam(value = "size", defaultValue = "10") Integer size) {
		if (StringUtils.isEmpty(openId)) {
			log.error("【查询订单列表】openId为空");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		PageRequest request = PageRequest.of(page, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(openId, request);
		//转Data--->long
		return ResultVoUtil.success(orderDTOPage.getContent());

	}

	//订单详情
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam("openId") String openId,
									 @RequestParam("orderId") String orderId) {
		OrderDTO orderDTO = buyerService.findOrderOne(openId, orderId);
		return ResultVoUtil.success(orderDTO);

	}


	//取消订单
	@PostMapping("/cancel")
	public ResultVO cancel(@RequestParam("openId") String openId,
						   @RequestParam("orderId") String orderId) {
		buyerService.cancelOrder(openId, orderId);
		return ResultVoUtil.success();
	}
}

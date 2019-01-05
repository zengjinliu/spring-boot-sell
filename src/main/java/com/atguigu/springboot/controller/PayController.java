package com.atguigu.springboot.controller;

import ch.qos.logback.classic.Logger;
import com.atguigu.springboot.dto.OrderDTO;
import com.atguigu.springboot.enums.ResultEnum;
import com.atguigu.springboot.exception.SellException;
import com.atguigu.springboot.service.serviceInterface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/27 11:04
 */
@Controller
@RequestMapping("/pay")
public class PayController {

	@Autowired
	OrderService orderService;

	public void creat(@RequestParam("orderId") String orderId,
					  @RequestParam("returnUrl") String returnUrl) {
		OrderDTO orderDTO = orderService.findOne(orderId);
		if (orderDTO == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		//发起支付
	}
}

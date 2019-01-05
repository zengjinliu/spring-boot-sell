package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.dto.OrderDTO;
import com.atguigu.springboot.service.serviceInterface.PayServicer;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 * @Description: 支付模块的service层
 * @date 2018/11/27 11:08
 */
@Service
public class PayServicerImpl implements PayServicer {
	@Override
	public void create(OrderDTO orderDTO) {
		BestPayServiceImpl bestPayService = new BestPayServiceImpl();

	}
}

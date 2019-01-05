package com.atguigu.springboot.converter;

import com.atguigu.springboot.bean.OrderDetail;
import com.atguigu.springboot.dto.OrderDTO;
import com.atguigu.springboot.enums.ResultEnum;
import com.atguigu.springboot.exception.SellException;
import com.atguigu.springboot.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/26 16:13
 */

/**
 * 字符串类型的数据转成list
 */
@Slf4j
public class OrderForm2OrderDTO {
	public static OrderDTO convert(OrderForm orderForm){
		Gson gson = new Gson();

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		orderDTO.setBuyerOpenid(orderForm.getOpenId());
		orderDTO.setBuyerAddress(orderForm.getAddress());

		List<OrderDetail> orderDetailList = new ArrayList<>();
		try {
			orderDetailList = gson.fromJson(orderForm.getItems(),
					new TypeToken<List<OrderDetail>>(){}.getType());
		} catch (JsonSyntaxException e) {
			log.error("【对象转换】 错误, String={}",orderForm.getItems());
			throw  new SellException(ResultEnum.PARAM_ERROR);
		}

		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;

	}
}

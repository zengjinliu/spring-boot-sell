package com.atguigu.springboot.controller;

import com.atguigu.springboot.bean.OrderDetail;
import com.atguigu.springboot.dto.OrderDTO;
import com.atguigu.springboot.enums.ResultEnum;
import com.atguigu.springboot.exception.SellException;
import com.atguigu.springboot.service.serviceInterface.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
 * @Description: 卖家端订单
 * @date 2018/11/27 13:15
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellOrderController {
	@Autowired
	OrderService orderService;

	/**
	 * @param page 从第1页开始
	 * @param size 一页有多少条数据
	 * @return
	 */
//	订单列表
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
							 @RequestParam(value = "size",defaultValue = "4") Integer size,
							 Map<String,Object> map){
		PageRequest request = PageRequest.of(page-1,size);
		Page<OrderDTO> orderDTOPage = orderService.list(request);
		map.put("orderDTOPage",orderDTOPage);
		map.put("page",page);
		return new ModelAndView("order/list",map);
	}


//	取消订单
	@GetMapping("/cancel")
	public ModelAndView cancel(@RequestParam( "orderId") String orderId,
							   Map<String,Object> map){
		OrderDTO orderDTO = null;
		try {
			orderDTO = orderService.findOne(orderId);
			if (orderDTO.getOrderStatus().equals(2)){
				map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMsg());
				map.put("url","/sell/seller/order/list");
				return new ModelAndView("common/error",map);
			}
			orderService.cancel(orderDTO);
		} catch (SellException e) {
			if (orderDTO==null){
				log.error("【卖家端取消订单】 发生异常");
				map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMsg());
				map.put("url","/sell/seller/order/list");
				return new ModelAndView("common/error",map);
			}
		}

		map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
		map.put("url","/sell/seller/order/list");
		return new ModelAndView("common/success",map);
	}

//	订单详情
	@GetMapping("/detail")
	public ModelAndView detail(@RequestParam("orderId") String orderId,
							    Map<String,Object> map){
		OrderDTO orderDTO = new OrderDTO();
		try {
			orderDTO = orderService.findOne(orderId);
		} catch (SellException e) {
			if (orderDTO==null){
				log.error("【卖家端取消订单】 发生异常,e={}",e);
				map.put("msg",e.getMessage());
				map.put("url","/sell/seller/order/list");
				return new ModelAndView("common/error",map);
			}
		}
		map.put("orderDTO",orderDTO);
		return new ModelAndView("common/detail",map);
	}

//	完结订单
	@GetMapping("/finish")
	public ModelAndView finish(@RequestParam("orderId") String orderId,
							   Map<String,Object> map){
		OrderDTO orderDTO = new OrderDTO();
		try {
			orderDTO = orderService.findOne(orderId);
			orderService.finish(orderDTO);
		} catch (SellException e) {
			if (orderDTO==null){
				log.error("【卖家端完结订单】 发生异常,e={}",e);
				map.put("msg",e.getMessage());
				map.put("url","/sell/seller/order/list");
				return new ModelAndView("common/error",map);
			}
		}
		map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
		map.put("url","/sell/seller/order/list");
		return new ModelAndView("common/success",map);
	}
}

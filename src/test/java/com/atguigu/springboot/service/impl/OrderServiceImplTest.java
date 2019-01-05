package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.OrderDetail;
import com.atguigu.springboot.dto.OrderDTO;
import com.atguigu.springboot.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/26 10:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

	@Autowired
	private OrderServiceImpl orderService;

	private  final String BUYER_OPENID = "1001011";

	/**
	 * 创建订单的测试
	 */
	@Test
	public void creat() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("廖师兄");
		orderDTO.setBuyerAddress("慕课网");
		orderDTO.setBuyerPhone("123456789");
		orderDTO.setBuyerOpenid(BUYER_OPENID);
		List<OrderDetail> orderDetailList = new ArrayList<>();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProductId("2");
		orderDetail.setProductQuantity(1);
		orderDetailList.add(orderDetail);

//		OrderDetail o2 = new OrderDetail();
//		o2.setProductId("1");
//		o2.setProductQuantity(2);
//		orderDetailList.add(o2);
		orderDTO.setOrderDetailList(orderDetailList);
		OrderDTO result = orderService.create(orderDTO);
		log.info("【创建订单】 result={}", result);
		Assert.assertNotNull(result);

	}

	/**
	 * 查询单个定单测试
	 */
	@Test
	public void findOne() {
		OrderDTO result = orderService.findOne("15432026619587679");
		log.info("【查询单个订单】 result={}", result);
		Assert.assertEquals("15432026619587679",result.getOrderId());
//		System.out.println(result);
	}

	/**
	 * 查询订单列表
	 */
	@Test
	public void findList() {
		PageRequest request = PageRequest.of(0,10);

		Page<OrderDTO> orderDTOS = orderService.findList(BUYER_OPENID, request);
		System.out.println(orderDTOS);
		Assert.assertNotNull(orderDTOS);
	}

	/**
	 * 测试取消订单
	 */
	@Test
	public void cancel() {
		OrderDTO orderDTO = orderService.findOne("15432026619587679");
		OrderDTO result = orderService.cancel(orderDTO);
		Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
	}

	/**
	 * 测试完结订单
	 */
	@Test
	public void finish() {
		OrderDTO orderDTO = orderService.findOne("15432026619587679");
		OrderDTO result = orderService.finish(orderDTO);
		Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),result.getOrderStatus());
	}

	/**
	 * 测试支付订单
	 */
	@Test
	public void paid() {
		OrderDTO orderDTO = orderService.findOne("15432026619587679");
		OrderDTO result = orderService.paid(orderDTO);
		Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),result.getPayStatus());
	}

	@Test
	public void list() {
		PageRequest request = PageRequest.of(0,10);
		Page<OrderDTO> orderDTOS = orderService.list(request);
		Assert.assertNotNull(orderDTOS);
	}
}
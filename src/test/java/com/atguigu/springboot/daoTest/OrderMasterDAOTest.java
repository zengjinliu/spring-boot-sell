package com.atguigu.springboot.daoTest;

import com.atguigu.springboot.bean.OrderMaster;
import com.atguigu.springboot.dao.OrderMasterRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 13:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest {

	@Autowired
	OrderMasterRepository repository;

	@Test
	public void testFindAll(){
		List<OrderMaster> orderMasters = repository.findAll();
		Assert.assertNotNull(orderMasters);
	}
	@Test
	public void testFindOne() {
		OrderMaster orderMaster = repository.findById("1").get();
		Assert.assertEquals(1,orderMaster.getOrderId());
	}
	@Test
	public void test01(){
		PageRequest pageRequest = PageRequest.of(0,2);
		Page<OrderMaster> orderMasters = repository.findByBuyerOpenid("", pageRequest);
		Assert.assertNotEquals(0,orderMasters.getTotalElements());
	}
	@Test
	public void testSave(){
		OrderMaster orderMaster= new OrderMaster();
		orderMaster.setOrderId("1");
		orderMaster.setBuyerName("刘增金");
		orderMaster.setBuyerPhone("18379254458");
		orderMaster.setBuyerAddress("大一广场");
		orderMaster.setBuyerOpenid("啊King");
		orderMaster.setOrderAmount(new BigDecimal(2));
		orderMaster.setOrderStatus(0);
		orderMaster.setPayStatus(1);
		OrderMaster master = repository.save(orderMaster);
		Assert.assertNotEquals(0,master);

	}
}

package com.atguigu.springboot.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.atguigu.springboot.bean.ProductInfo;
import lombok.extern.slf4j.Slf4j;
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

import static org.junit.Assert.*;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 18:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {
	@Autowired
	ProductServiceImpl productService;

	@Test
	public void findOne() {
		ProductInfo productInfo = productService.findOne("1");
		Assert.assertNotNull(productInfo);
	}

	@Test
	public void findUpAll() {
		List<ProductInfo> upAll = productService.findUpAll();
		Assert.assertNotEquals(0,upAll.size());
	}

	@Test
	public void findAll() {
		PageRequest request = PageRequest.of(0,2);
		Page<ProductInfo> productInfos = productService.findAll(request);
//		System.out.println(productInfos.getTotalElements());
	}

	@Test
	public void save() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("2");
		productInfo.setProductName("瘦肉粥");
		productInfo.setProductPrice(new BigDecimal(2.3));
		productInfo.setProductStock(12);
		productInfo.setProductDescription("好吃的不行");
		productInfo.setProductIcon("aa");
		productInfo.setProductStatus(1);
		productInfo.setCategoryType(2);
		ProductInfo productInfo1 = productService.save(productInfo);
		Assert.assertNotNull(productInfo);

	}
}
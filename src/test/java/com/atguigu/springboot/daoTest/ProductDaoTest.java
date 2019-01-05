package com.atguigu.springboot.daoTest;

import com.atguigu.springboot.bean.ProductInfo;
import com.atguigu.springboot.dao.ProductInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason
 * @Description: 测试商品的DAO层
 * @date 2018/11/22 15:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductDaoTest {

	@Autowired
	ProductInfoRepository repository;

	@Test
	public void test01(){
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("1");
		productInfo.setProductName("鸡蛋面");
		productInfo.setProductPrice(new BigDecimal(2.3));
		productInfo.setProductStock(12);
		productInfo.setProductDescription("好吃的不行");
		productInfo.setProductIcon("aa");
		productInfo.setProductStatus(1);
		productInfo.setCategoryType(2);

		ProductInfo result = repository.save(productInfo);
		Assert.assertNotNull(result);

	}

	@Test
	public void test02(){
		List<ProductInfo> list = repository.findByProductStatus(1);
		Assert.assertNotEquals(0,list.size());
	}


}

package com.atguigu.springboot;

import com.atguigu.springboot.bean.ProductCategory;
import com.atguigu.springboot.dao.ProductRepository;
import com.atguigu.springboot.service.impl.CategoryServiceImpl;
import com.atguigu.springboot.service.impl.ProductServiceImpl;
import com.atguigu.springboot.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootSellApplicationTests {

	@Autowired
	DataSource dataSource;
	//将dao层注入进来
	@Autowired
	ProductRepository repository;
	@Autowired
	CategoryServiceImpl categoryService;

	@Test
	public void contextLoads() throws SQLException {
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}
	@Test
	public void test(){
		Logger logger = LoggerFactory.getLogger(SpringBootSellApplicationTests.class);

		String name = "fa";
		String password = "123";
		logger.info("name: {},password: {}",name,password);
		logger.error("hello,world");

	}

	@Test
	@Transactional
	public void testProductCategory(){
		List<ProductCategory> categories = repository.findAll();
		for (ProductCategory category : categories) {
			System.out.println(category);
		}
		ProductCategory productCategory = repository.findById(2).get();
		productCategory.setCategoryType(3);
		repository.save(productCategory);
		repository.deleteById(1);
	}

	@Test
	public void test01(){
		List<Integer> list = Arrays.asList(1,2,3);
		List<ProductCategory> categories = repository.findByCategoryTypeIn(list);
		Assert.assertNotEquals(0,categories.size());

	}
	@Test
	public void test02(){
		ProductCategory productCategory = categoryService.findOne(1);
		System.out.println(productCategory);
	}
	@Test
	public void test03(){
		System.out.println(KeyUtil.genUUID());

	}

}

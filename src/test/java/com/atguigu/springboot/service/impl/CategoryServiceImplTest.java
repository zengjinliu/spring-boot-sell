package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 14:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {

	@Autowired
	private CategoryServiceImpl categoryService;

	@Test
	public void findAll() {
		List<ProductCategory> all = categoryService.findAll();
		Assert.assertNotEquals(null,all.size());
	}

	@Test
	public void findOne() {
		ProductCategory one = categoryService.findOne(1);
		System.out.println(one);
		Assert.assertEquals(new Integer(1),one.getCategoryId());
	}

	@Test
	public void findByCategoryTypeIn() {
		List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
		Assert.assertNotEquals(0,byCategoryTypeIn.size());
	}

	@Test
	public void save() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("周杰伦");
		productCategory.setCategoryType(5);
		categoryService.save(productCategory);
	}
}
package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Jason
 * @Description:
 * @date 2018/12/4 18:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

	@Autowired
	SellerServiceImpl sellerService;

	@Test
	public void findSellerInfoByOpenid() {

		SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid("abc");
		Assert.assertNotNull(sellerInfo);


	}
}
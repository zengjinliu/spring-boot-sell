package com.atguigu.springboot.daoTest;

import com.atguigu.springboot.bean.SellerInfo;
import com.atguigu.springboot.dao.SellerInfoRepository;
import com.atguigu.springboot.util.KeyUtil;
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
 * @date 2018/12/4 18:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest {

	@Autowired
	SellerInfoRepository sellerInfoRepository;

	@Test
	public void save(){
		SellerInfo sellerInfo = new SellerInfo();
		sellerInfo.setSellerId(KeyUtil.genUniqueKey());
		sellerInfo.setUsername("admin");
		sellerInfo.setPassword("admin");
		sellerInfo.setOpenid("abc");
		SellerInfo result = sellerInfoRepository.save(sellerInfo);
		Assert.assertNotNull(result);

	}

	@Test
	public void findByOpenid() {

		SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("abc");
		Assert.assertNotNull(sellerInfo);


	}
}
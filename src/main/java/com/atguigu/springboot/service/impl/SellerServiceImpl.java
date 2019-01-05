package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.SellerInfo;
import com.atguigu.springboot.dao.SellerInfoRepository;
import com.atguigu.springboot.service.serviceInterface.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 * @Description:
 * @date 2018/12/4 18:52
 */
@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	SellerInfoRepository sellerInfoRepository;

	@Override
	public SellerInfo findSellerInfoByOpenid(String openid) {
		return sellerInfoRepository.findByOpenid(openid);
	}
}

package com.atguigu.springboot.service.serviceInterface;

import com.atguigu.springboot.bean.SellerInfo;

/**
 * @author Jason
 * @Description:
 * @date 2018/12/4 18:51
 */
public interface SellerService {
	SellerInfo findSellerInfoByOpenid(String openid);
}

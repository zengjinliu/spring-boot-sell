package com.atguigu.springboot.dao;

import com.atguigu.springboot.bean.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 * @Description:
 * @date 2018/12/4 18:39
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
	SellerInfo findByOpenid(String openid);
}

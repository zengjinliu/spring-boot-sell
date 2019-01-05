package com.atguigu.springboot.dao;

import com.atguigu.springboot.bean.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 15:45
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
	List<ProductInfo> findByProductStatus(Integer productStatus);
}

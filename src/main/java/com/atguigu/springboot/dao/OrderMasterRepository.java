package com.atguigu.springboot.dao;

import com.atguigu.springboot.bean.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 8:52
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

	Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);

}

package com.atguigu.springboot.dao;

import com.atguigu.springboot.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 13:26
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
	List<OrderDetail> findByOrderId(String orderId);
}

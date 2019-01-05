package com.atguigu.springboot.service.serviceInterface;

import com.atguigu.springboot.dto.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/27 10:25
 */
@Service
public interface BuyerService {
	//查询一个订单
	OrderDTO findOrderOne(String openId, String orderId);

	//取消订单
	OrderDTO cancelOrder(String openId,String orderId);
}

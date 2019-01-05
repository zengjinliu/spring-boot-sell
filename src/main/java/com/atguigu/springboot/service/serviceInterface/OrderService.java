package com.atguigu.springboot.service.serviceInterface;

import com.atguigu.springboot.dto.OrderDTO;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Jason
 * @Description: 对订单主表进行操作
 * @date 2018/11/23 15:28
 */
public interface OrderService {
	//创建订单
	OrderDTO create(OrderDTO orderDTO);

	//查询单个订单
	OrderDTO findOne(String orderId);

	//查询订单列表,并进行分页
	Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

	//取消订单
	OrderDTO cancel(OrderDTO orderDTO);

	//完结订单
	OrderDTO finish(OrderDTO orderDTO);

	//支付订单
	OrderDTO paid(OrderDTO orderDTO);

	//卖家端查询订单总数
	Page<OrderDTO> list( Pageable pageable);
}

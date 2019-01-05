package com.atguigu.springboot.dto;

import com.atguigu.springboot.bean.OrderDetail;
import com.atguigu.springboot.enums.OrderStatusEnum;
import com.atguigu.springboot.enums.PayStatusEnum;
import com.atguigu.springboot.util.EnumUtil;
import com.atguigu.springboot.util.serializer.Date2Long;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 15:36
 */
@Data
public class OrderDTO {

	//订单id
	private String orderId;

	//买家用户名
	private String buyerName;

	//买家电话
	private String buyerPhone;

	//买家地址
	private String buyerAddress;

	//买家微信id
	private String buyerOpenid;

	//订单数量
	private BigDecimal orderAmount;

	//订单状态 默认状态0新订单，1旧订单
	private Integer orderStatus= OrderStatusEnum.NEW.getCode();

	//支付状态 默认0为未支付，1已支付
	private Integer payStatus = PayStatusEnum.WAIT.getCode();

	//创建时间
	@JsonSerialize(using = Date2Long.class)
	private Date creatTime;

	//跟新时间
	@JsonSerialize(using = Date2Long.class)
	private Date updateTime;

	//过度数据
	@JsonIgnore
	private List<OrderDetail> orderDetailList;

	@JsonIgnore
	public OrderStatusEnum getOrderStatusEnum(){
		return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
	}
	@JsonIgnore
	public PayStatusEnum getPayStatusEnum(){
		return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
	}
}

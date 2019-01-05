package com.atguigu.springboot.bean;

import com.atguigu.springboot.enums.OrderStatusEnum;
import com.atguigu.springboot.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 8:33
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
	//订单id
	@Id
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
	private Date creatTime;

	//跟新时间
	private Date updateTime;

}

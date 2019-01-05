package com.atguigu.springboot.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jason
 * @Description: 订单详情表
 * @date 2018/11/23 13:16
 */
@Data
@Entity
@DynamicUpdate
public class OrderDetail{
	//订单详情id
	@Id
	private String detailId;

	//订单id
	private String orderId;

	//商品id
	private String productId;

	//商品名称
	private String productName;

	//商品价格
	private BigDecimal productPrice;

	//商品数量
	private Integer productQuantity;

	//商品图片
	private String productIcon;

	//创建时间
	private Date creatTime;

	//更新时间
	private Date updateTime;
}

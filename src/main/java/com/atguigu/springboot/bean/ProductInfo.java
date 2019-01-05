package com.atguigu.springboot.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 15:35
 */
@Data
@DynamicUpdate
@Entity
public class ProductInfo {
	//商品Id
	@Id
	private String productId;

	//商品名称
	private String productName;

	//商品价格
	private BigDecimal productPrice;

	//商品库存
	private Integer productStock;

	//商品描述
	private String productDescription;

	//商品图片
	private String productIcon;
	//0正常1下架，商品状态
	private Integer productStatus;

	//商品类目
	private Integer categoryType;

	//创建时间
	private Date creatTime;

	//跟新时间
	private Date updateTime;

}

package com.atguigu.springboot.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 8:16
 */

@Entity
@Data
@DynamicUpdate//动态更新
public class ProductCategory {


	//类目表ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	//类目名
	private String categoryName;

	//类目类型
	private Integer categoryType;

	//创建时间
	private Date creatTime;

	//跟新时间
	private Date updateTime;




}

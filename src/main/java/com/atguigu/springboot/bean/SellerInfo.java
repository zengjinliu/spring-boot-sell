package com.atguigu.springboot.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author Jason
 * @Description:
 * @date 2018/12/4 18:33
 */
@Entity
@DynamicUpdate
@Data
public class SellerInfo {

	@Id
	private String sellerId;

	private String username;

	private String password;

	private String openid;

//	private Timestamp createTime;
//
//	private Timestamp updateTime;



}

package com.atguigu.springboot.form;

import com.atguigu.springboot.dto.CartDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/26 15:52
 */
@Data
public class OrderForm {
	//买家姓名
	@NotEmpty(message = "姓名必填")
	private String name;

	//买家电话
	@NotEmpty(message = "买家电话必填")
	private String phone;

	//买家地址
	@NotEmpty(message = "买家地址必填")
	private String address;

	//买家微信号
	@NotEmpty(message = "用户微信号")
	private String openId;

	//购物车
	@NotEmpty(message = "购物车不能为空")
	private String items;

}

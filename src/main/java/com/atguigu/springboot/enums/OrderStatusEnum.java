package com.atguigu.springboot.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 13:01
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

	NEW(0,"新订单"),
	FINISH(1,"完结"),
	CANCEL(2,"已取消");

	private Integer code;
	private String msg;

	OrderStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}

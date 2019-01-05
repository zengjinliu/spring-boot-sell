package com.atguigu.springboot.enums;

import lombok.Getter;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 13:12
 */
@Getter
public enum PayStatusEnum implements CodeEnum{


	WAIT(0,"等待支付"),
	SUCCESS(1,"支付成功");

	private Integer code;
	private String msg;

	PayStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}

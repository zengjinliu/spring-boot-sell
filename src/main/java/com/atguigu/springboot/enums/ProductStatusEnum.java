package com.atguigu.springboot.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 17:34
 */
@Getter
public enum ProductStatusEnum {
	UP(0,"正常"),
	DOWN(1,"下架");

	private Integer code;
	private String message;

	ProductStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}

package com.atguigu.springboot.exception;

import com.atguigu.springboot.enums.ResultEnum;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 15:53
 */
public class SellException extends RuntimeException {

	private Integer code;
	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}
//	public SellException(Integer code,String msg){
//		super(msg);
//		this.code=code;
//	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}

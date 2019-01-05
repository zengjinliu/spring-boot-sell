package com.atguigu.springboot.VO;

import lombok.Data;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 9:35
 */
@Data
public class ResultVO<T> {
	//错误码
	private Integer code;
	//信息
	private String msg;
	//数据
	private T data;
}

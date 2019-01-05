package com.atguigu.springboot.util;

import com.atguigu.springboot.enums.CodeEnum;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/27 18:25
 */
public class EnumUtil {
	public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
		for (T each : enumClass.getEnumConstants()) {
			if (code.equals(each.getCode())){
				return each;
			}

		}
		return null;
	}

}

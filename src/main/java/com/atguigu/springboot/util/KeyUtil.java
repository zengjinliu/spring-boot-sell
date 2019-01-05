package com.atguigu.springboot.util;

import java.sql.Time;
import java.util.Random;
import java.util.UUID;

/**
 * @author Jason
 * @Description: 生成随机数的工具类
 * @date 2018/11/23 17:31
 */
public class KeyUtil {

	//随机生成随机数
	public synchronized static String  genUniqueKey(){
		Random random = new Random();
		int i = random.nextInt(9000)+1000;
		return System.currentTimeMillis()+String.valueOf(i);
	}
	//随机生成UUid
	public static String genUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-","");
	}

}

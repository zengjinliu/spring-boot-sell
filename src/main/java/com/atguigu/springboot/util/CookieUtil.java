package com.atguigu.springboot.util;

import org.apache.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jason
 * @Description:
 * @date 2018/12/5 15:55
 */
public class CookieUtil {
	public static  void set(HttpServletResponse response,
					String name,
					String value,
					int maxAge){
		Cookie cookie = new Cookie(name,value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static void get(){


	}
}

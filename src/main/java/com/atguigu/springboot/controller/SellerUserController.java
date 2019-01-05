package com.atguigu.springboot.controller;

import com.atguigu.springboot.bean.SellerInfo;
import com.atguigu.springboot.constant.CookieConstant;
import com.atguigu.springboot.constant.RedisConstant;
import com.atguigu.springboot.enums.ResultEnum;
import com.atguigu.springboot.service.serviceInterface.SellerService;
import com.atguigu.springboot.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Jason
 * @Description:
 * @date 2018/12/5 15:32
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

	@Autowired
	SellerService sellerService;
	@Autowired
	StringRedisTemplate redisTemplate;

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("openid") String openid,
							  HttpServletResponse response,
							  Map<String,Object> map){

		//查询用户信息
		SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
		if (sellerInfo == null){
			map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
			map.put("url","/sell/seller/order/list");
			return new ModelAndView("commen/error",map);
		}

		//将token设置到Redis
		String token = UUID.randomUUID().toString();
		Integer expire = RedisConstant.EXPIRE;

		redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);

		//将token设置到Cookie
		CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);

		return new ModelAndView("redirect:/sell/seller/order/list");
	}

	public ModelAndView logout(){
		return null;
	}

}

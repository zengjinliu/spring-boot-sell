package com.atguigu.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @ClassName RedisLock
 * @Description redis分布式锁处理高并发
 * @Author Administrator
 * @Date 2018/12/13 8:45
 */

@Component
@Slf4j
public class RedisLock {

	@Autowired
	StringRedisTemplate redisTemplate;

	/**
	 *
	 * @param key productId
	 * @param value 当前时间+设置的过期时间
	 * @return
	 */
	public boolean lock(String key,String value){
		//拿到锁
		if (redisTemplate.opsForValue().setIfAbsent(key,value)){
			return true;
		}
		/*
		防止死锁
		 */
		//拿到当前线程的value值
		String currentValue = redisTemplate.opsForValue().get(key);
		//判断锁是否过期
		if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)<System.currentTimeMillis()){
			String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
			if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 解锁
	 * @param key
	 * @param value
	 */
	public void unlock(String key, String value){
		try {
			String currentValue = redisTemplate.opsForValue().get(key);
			if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
				redisTemplate.opsForValue().getOperations().delete(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

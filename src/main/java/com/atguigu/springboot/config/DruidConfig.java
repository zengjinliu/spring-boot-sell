package com.atguigu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/21 14:02
 */
@Configuration
public class DruidConfig {

	//配置druid数据源
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druid(){
		return new DruidDataSource();
	}

	//配置后台数据源
	@Bean
	public ServletRegistrationBean statViewServlet(){

		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

		Map<String,String> initParams = new HashMap<>();
		initParams.put("loginUsername","admin");
		initParams.put("loginPassword","123456");
		initParams.put("allow","");
		initParams.put("deny","192.168.5.235");
		bean.setInitParameters(initParams);
		return bean;
	}
	@Bean
	public FilterRegistrationBean webStatFilter(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());

		Map<String,String> initParams = new HashMap<>();
		initParams.put("exclusions","*.js,*.css,/druid/*");

		bean.setInitParameters(initParams);

		bean.setUrlPatterns(Arrays.asList("/*"));

		return  bean;
	}







}

package com.ppmg.ei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ppmg.common.interceptors.FuncPermissionInterceptor;


@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// super.addViewControllers(registry);
		// 浏览器发送/请求来到index
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/fund").setViewName("fundName");
	}
	
	@Override    
    public void addInterceptors(InterceptorRegistry registry) {
		//注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new FuncPermissionInterceptor()).addPathPatterns("/**");    
    }
}

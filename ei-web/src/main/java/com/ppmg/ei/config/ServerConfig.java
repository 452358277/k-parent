package com.ppmg.ei.config;

import com.founder.core.filter.UimSSOFilter;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class ServerConfig {

	//注册三大组件servlet、filter、listener
	/*@Bean
	public ServletRegistrationBean myServlet(){
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
		return servletRegistrationBean;
	}*/
	@Bean
	public FilterRegistrationBean uimSSOFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new UimSSOFilter());
		filterRegistrationBean.addInitParameter("UIMSSOEventHandlerClass", "com.founder.core.filter.CISSSSOEventHandlerImpl");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.woff,*.woff2,/swagger-resources,/v2,/swagger-ui.html,/login,/generate,/getUserInfo,/updatePassword");
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
		return filterRegistrationBean;
	}
	
/*	@Bean
	public ServletListenerRegistrationBean<MyListener> myListener(){
		ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
		return servletListenerRegistrationBean;
	}*/
	
	//嵌入式servlet容器定制器，与application.yml中的server.xxx基本对应
	/*@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
		return new EmbeddedServletContainerCustomizer() {
			
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				//container.setPort(8083);	
			}
		};
	}*/
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		return new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatEmbeddedServletContainer(tomcat);
			}

			@Override
			protected void postProcessContext(Context context) {
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/YHKG_DS_BASE");
				resource.setType(DataSource.class.getName());
				resource.setAuth("Container");
				resource.setType("javax.sql.DataSource");
				resource.setScope("Sharable");
				resource.setProperty("driverClassName", driverClassName);
				resource.setProperty("url", url);
				resource.setProperty("username", "BASE");
				resource.setProperty("password", "BASE");

				context.getNamingResources().addResource(resource);
			}
		};
	}
}

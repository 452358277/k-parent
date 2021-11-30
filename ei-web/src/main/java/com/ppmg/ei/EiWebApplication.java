package com.ppmg.ei;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDubboConfiguration
@EnableSwagger2
@ComponentScan(basePackages={"com.ppmg","com.founder.ssm"})
//@EnableScheduling
public class EiWebApplication{

	public static void main(String[] args) {
		SpringApplication.run(EiWebApplication.class, args);
	}
	
}

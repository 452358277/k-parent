package com.ppmg.ei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@SpringBootApplication
@EnableDubboConfiguration
@ComponentScan(basePackages={"com.ppmg.ei","com.founder.ssm"})
public class EiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EiServiceApplication.class, args);
	}
}

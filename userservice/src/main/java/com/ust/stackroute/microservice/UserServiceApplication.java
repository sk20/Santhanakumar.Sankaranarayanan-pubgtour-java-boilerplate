package com.ust.stackroute.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ust.stackroute.microservice.jwtfilter.JwtFilter;

@SpringBootApplication
public class UserServiceApplication {

//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setFilter(new JwtFilter());
//		registrationBean.addUrlPatterns("/api/*");
//		return registrationBean;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}

package com.demo.mvc.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.demo.mvc")
public class DemoMvcConfig extends WebMvcConfigurerAdapter{
	
	
	  @Bean 
	  public InternalResourceViewResolver ViewRzsolver( ) {
	  
	  InternalResourceViewResolver vr = new InternalResourceViewResolver();
 
      vr.setPrefix("/views/");
      vr.setSuffix(".jsp");
	  return vr; 
	  }
	 
	

}

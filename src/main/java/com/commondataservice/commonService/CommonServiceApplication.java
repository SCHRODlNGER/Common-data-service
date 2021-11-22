package com.commondataservice.commonService;

import com.commondataservice.commonService.controllers.CommonDataController;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class CommonServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CommonServiceApplication.class, args);
		context.getBean(CommonDataController.class).seedDB();
	}

}

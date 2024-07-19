package com.awaion.demo013;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Demo013Application {

//	public static void main(String[] args) {
//		SpringApplication.run(Demo013Application.class, args);
//	}

	public static void main(String[] args) {
		var ioc = SpringApplication.run(Demo013Application.class, args);
		String[] names = ioc.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}

}

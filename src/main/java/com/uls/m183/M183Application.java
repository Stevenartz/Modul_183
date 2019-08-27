package com.uls.m183;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.uls.hello"})
public class M183Application {

	public static void main(String[] args) {
		SpringApplication.run(M183Application.class, args);
	}

}

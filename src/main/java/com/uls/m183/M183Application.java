package com.uls.m183;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.uls.controller"})
public class M183Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder().bannerMode(Mode.CONSOLE).sources(M183Application.class).run(args);
	}

}

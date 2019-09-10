package com.uls.m183;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.uls.controller"})
public class M183Application {

	public static void main(String[] args) {
		final Logger LOGGER = LoggerFactory.getLogger(M183Application.class);
		new SpringApplicationBuilder()
			.logStartupInfo(false)
			.bannerMode(Mode.CONSOLE)
			.sources(M183Application.class)
			.run(args);
		LOGGER.info("Application successfully started and running!");
	}

}

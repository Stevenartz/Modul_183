package com.uls.m183;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * This class is essential to start the program at all.
 * Created on 2019-08-27
 * 
 * @author Stefan Ulrich
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.uls.controller"})
public class M183Application {

	// Stevenartz, password
	// Samu, pw
	// Braibrai, passwortpasswort
	
	// keystore-Kennwort: Yf8aKPJAoMRQ
	// Schlüsselkennwort für <tomcat>: ro3ELPTWgCbZ
	
	/**
	 * Start of the whole program.
	 * 
	 * @param args, just the JVM args.
	 */
	public static void main(String[] args) {
		final Logger LOGGER = LoggerFactory.getLogger(M183Application.class);
		new SpringApplicationBuilder()
			.logStartupInfo(false)
			.bannerMode(Mode.CONSOLE)
			.sources(M183Application.class)
			.run(args);
		LOGGER.info("Application started and running!");
	}

}

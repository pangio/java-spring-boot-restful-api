package com.pangio.magazine.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pangio.magazine.api.domain.Magazine;
import com.pangio.magazine.api.repository.MagazineRepository;

/**
 * Main class. Performs the setup of the Spring Boot application. Also creates
 * sample data.
 * 
 * @author pangio
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackages = "com.pangio.magazine")
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication
				.run(Application.class);
		MagazineRepository magazineRepository = context
				.getBean(com.pangio.magazine.api.repository.MagazineRepository.class);

		magazineRepository.save(new Magazine("Times"));
		magazineRepository.save(new Magazine("Western Times"));
		magazineRepository.save(new Magazine("ESPN Magazine"));
		
	}
}

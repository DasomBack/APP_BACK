package com._thefull.dasom_app_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DasomAppDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DasomAppDemoApplication.class, args);
	}

}

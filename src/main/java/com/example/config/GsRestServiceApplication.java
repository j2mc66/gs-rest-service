package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example"})
@EntityScan("com.example")
@EnableJpaRepositories("com.example")
public class GsRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsRestServiceApplication.class, args);
	}
}

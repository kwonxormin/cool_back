package com.cool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoolApplication.class, args);
	}

}

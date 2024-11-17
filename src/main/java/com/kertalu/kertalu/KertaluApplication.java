package com.kertalu.kertalu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class KertaluApplication {

	public static void main(String[] args) {
		SpringApplication.run(KertaluApplication.class, args);
	}

}

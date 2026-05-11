package com.hms_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AiService {

	public static void main(String[] args) {
		SpringApplication.run(AiService.class, args);
	}

}

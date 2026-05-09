package com.hms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayMsApplication {

	public static void main(String[] args) {
		System.out.println("GateWay started...");
		SpringApplication.run(GatewayMsApplication.class, args);
	}

}

package com.inikitagricenko.demosam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.inikitagricenko"})
public class DemosamApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemosamApplication.class, args);
	}

}

package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.crm")
public class CrmWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmWebApplication.class, args);
	}

}

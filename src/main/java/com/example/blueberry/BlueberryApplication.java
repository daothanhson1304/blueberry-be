package com.example.blueberry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BlueberryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueberryApplication.class, args);
	}

}

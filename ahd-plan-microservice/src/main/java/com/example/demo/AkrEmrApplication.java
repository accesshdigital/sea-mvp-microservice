package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@ServletComponentScan
@SpringBootApplication
public class AkrEmrApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkrEmrApplication.class, args);
	}

}

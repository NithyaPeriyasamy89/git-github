package com.sample.sampleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sample.controller","com.sample.service","com.sample.repository","com.sample.sampleproject"})
public class SampleprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleprojectApplication.class, args);
	}

}

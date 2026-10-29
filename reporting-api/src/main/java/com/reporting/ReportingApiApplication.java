package com.reporting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class ReportingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingApiApplication.class, args);
		log.info("Reporting Application started");
	}

}

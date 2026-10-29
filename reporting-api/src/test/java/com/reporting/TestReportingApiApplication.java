package com.reporting;

import org.springframework.boot.SpringApplication;

public class TestReportingApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(ReportingApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

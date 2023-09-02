package com.gabriel.bcimapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bedatadriven.jackson.datatype.jts.JtsModule;

@SpringBootApplication
public class BcimapiApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.config.name", "application-dev");
		SpringApplication.run(BcimapiApplication.class, args);
	}
    //https://stackoverflow.com/questions/74165239/is-postgispg10dialect-superseded-by-postgresqldialect-now	

	@Bean
	public JtsModule jtsModule() {
		// This module will provide a Serializer for geometries
		return new JtsModule();
	}

}

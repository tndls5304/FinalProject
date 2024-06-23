package com.kh.works;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan
public class WorksApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorksApplication.class, args);
	}

}

package com.raja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.raja.respositary.LoginRepositary;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = LoginRepositary.class)
public class MyapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyapplicationApplication.class, args);
	}

}

package com.todo_management_working;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoManagementWorkingApplication {

	@Bean
	public ModelMapper modelMapper(){ //what is this
		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(TodoManagementWorkingApplication.class, args);
	}

}

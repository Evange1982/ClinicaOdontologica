package com.backend.digitalhouse.ClinicaOdontologica;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.SQLException;

@SpringBootApplication
public class ClinicaOdontologicaApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);

		LOGGER.info("ClinicaOdontologica is now running...");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}
}

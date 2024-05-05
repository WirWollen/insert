package com.parse.steam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan({"com.parse.steam.entities"})
@EnableJpaRepositories(basePackages = {"com.parse.steam.repo"})
@SpringBootApplication
@Configuration
public class SteamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SteamApplication.class, args);
	}

}

package io.github.wferdinando.helpdesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.wferdinando.helpdesk.services.DBService;

@Configuration
public class TestConfig {

	private DBService dbService;

	public TestConfig(DBService dbService) {
		this.dbService = dbService;
	}

	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}
}

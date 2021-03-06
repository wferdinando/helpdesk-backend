package io.github.wferdinando.helpdesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.github.wferdinando.helpdesk.services.DBService;

@Configuration
@Profile(value = "test")
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

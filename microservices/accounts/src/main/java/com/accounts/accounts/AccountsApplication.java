package com.accounts.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableFeignClients
@OpenAPIDefinition(
	info = @Info(
		title = "Accounts microservice REST API Documentation",
		description = "Accounts microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
			name = "Arannamoy-Mondal",
			email="Not Available",
			url="Not Available"
		),
		license = @License(
			name = "Apache 2.0",
			url="Not Available"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Accounts microservice REST API Documentation",
		url="Not Available"
	)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}

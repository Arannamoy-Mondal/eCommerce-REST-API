package com.aranna.backend.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.authorizeHttpRequests(auth->
            auth.requestMatchers("/**")
            .permitAll()
        )
        .csrf(customizer->customizer.disable())
        .httpBasic(customizer->customizer.disable())
        .formLogin(customizer->customizer.disable())
        .build();
    }
}

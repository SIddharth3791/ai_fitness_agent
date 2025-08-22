package com.fitness.apiGateway.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeExchange(exchange -> exchange.anyExchange().authenticated())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
				.build();
		
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration() {};
		configuration.setAllowedOrigins(List.of("http://localhost:9000/"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONs"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","X-User-ID"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source =  new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/**", configuration);
		return source;
	}	

}

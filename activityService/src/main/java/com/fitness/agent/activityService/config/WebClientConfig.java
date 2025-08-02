package com.fitness.agent.activityService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	@Value("${user-service.base-url}")
	private String userServiceBaseURL;

    @Bean
    @LoadBalanced
    WebClient.Builder webClientBuilder(){
		return WebClient.builder();
	}
    
    @Bean
     WebClient userServiceWebClient(WebClient.Builder webClientBuilder) {
    	return webClientBuilder
    				.baseUrl(userServiceBaseURL)
    				.build();
    }
	

}

package com.fitness.agent.activityService.service;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {
	
	
	private final WebClient userServiceWebClient;
	
	public boolean validateUser(String userId) {
		log.info("Validating User for UserId {} ", userId);
		try {
			return userServiceWebClient.get()
					.uri("/api/users/{userId}/validate",userId)
					.retrieve()
					.bodyToMono(Boolean.class)
					.block();
		}catch (WebClientResponseException ex) {
			log.error("Error while Validating User for UserId {} ", userId);
			if (ex.getStatusCode().value() == HttpStatus.SC_NOT_FOUND) {
				throw new RuntimeException("User Not Found");
				
			}else {
				throw new RuntimeException("Error When Validating User");
			}
		}
	}

}

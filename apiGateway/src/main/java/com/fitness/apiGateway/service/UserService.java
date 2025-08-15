package com.fitness.apiGateway.service;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fitness.apiGateway.model.RegisterRequest;
import com.fitness.apiGateway.model.UserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

	private final WebClient userServiceWebClient;
	
	public Mono<Boolean> validateUser(String userId) {
		log.info("Validating User for UserId {} ", userId);
			return userServiceWebClient.get()
					.uri("/api/users/{userId}/validate",userId)
					.retrieve()
					.bodyToMono(Boolean.class)
					.onErrorResume(WebClientResponseException.class, ex ->{
						log.error("Error while Validating User for UserId {} ", userId);
						if (ex.getStatusCode().value() == HttpStatus.SC_NOT_FOUND) {
							return Mono.error(new RuntimeException("User Not Found"));
						}else {
							return Mono.error(new RuntimeException("Error When Validating User"));
						}
					});
	}
	
	public Mono<UserResponse> registerUser(RegisterRequest registerUser){
		log.info("User Registeration for User Email  {} ", registerUser.getEmail());
		return userServiceWebClient.post()
				.uri("/api/users/register")
				.bodyValue(registerUser)
				.retrieve()
				.bodyToMono(UserResponse.class)
				.onErrorResume(WebClientResponseException.class, ex ->{
					
					log.error("Error while Registering User  for User Email {} ", registerUser.getEmail());
					
					if (ex.getStatusCode().value() == HttpStatus.SC_BAD_REQUEST) {
						return Mono.error(new RuntimeException("User Not Found "+ ex.getMessage()));
					}else {
						return Mono.error(new RuntimeException("Error When Registering User "+ ex.getMessage()));
					}
				});
		
	}
}

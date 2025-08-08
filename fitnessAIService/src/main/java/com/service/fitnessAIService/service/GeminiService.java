package com.service.fitnessAIService.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.service.fitnessAIService.model.gemini.ContentRequest;

@Service
public class GeminiService {
	
	private final WebClient webClient;
	
	@Value("${gemini.api.url}")
	private String geminiApiUrl;
	
	
	@Value("${gemini.api.key}")
	private String geminiApiKey;
	
	
	public GeminiService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}
	
	public String getAnswer(String prompt) {

		
		ContentRequest geminiRequest = ContentRequest.builder()
											    .contents(Collections.singletonList(
											        ContentRequest.Content.builder()
											            .parts(Collections.singletonList(
											                ContentRequest.Part.builder()
											                    .text(prompt)
											                    .build()
											            ))
											            .build()
											    ))
											    .build();
		
		
		String responseString = webClient.post()
									.uri(geminiApiUrl)
									.header("X-goog-api-key", geminiApiKey)
									.header("Content-Type", "application/json")
									.bodyValue(geminiRequest)
									.retrieve()
									.bodyToMono(String.class)
									.block();
		
		return responseString;
	}

}

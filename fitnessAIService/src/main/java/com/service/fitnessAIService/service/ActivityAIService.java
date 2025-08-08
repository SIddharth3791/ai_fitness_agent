package com.service.fitnessAIService.service;

import org.springframework.stereotype.Service;

import com.service.fitnessAIService.PromptConstants;
import com.service.fitnessAIService.model.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAIService {
	
	private final GeminiService geminiService;
	
	public String generateRecommendation(Activity activity) {
		String prompt = createPromptForActivity(activity);
		String geminiResponse = geminiService.getAnswer(prompt);
		return geminiResponse;
	}
	
	private String createPromptForActivity(Activity activity) {
		return String.format(PromptConstants.RECOMMENDATION_PROMPT, 
									activity.getType(), 
									activity.getDuration(), 
									activity.getCaloriesBurned(), 
									activity.getAdditionalMetrics());
		
	}

}

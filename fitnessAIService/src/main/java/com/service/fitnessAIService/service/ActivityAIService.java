package com.service.fitnessAIService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.fitnessAIService.PromptConstants;
import com.service.fitnessAIService.model.Activity;
import com.service.fitnessAIService.model.Recommendation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAIService {
	
	private final GeminiService geminiService;
	private final ObjectMapper mapper;
	
	private static final String ANALYSIS_NODE = "analysis";
	
	public Recommendation generateRecommendation(Activity activity) {
		// Generating Dynamic Prompt based on Activity
		String prompt = createPromptForActivity(activity);
		// Fetch Recommendation from Gemini AI API base on custom prompt
		String geminiResponse = geminiService.getAnswer(prompt);
		// Map The JSON Response from AI to Recommendation 
		return mapToRecommendation(activity,processAiResponse(geminiResponse));
	}
	
	private Recommendation mapToRecommendation(Activity activity, String recommendationContent) {

		try {
			JsonNode recommendationNode = mapper.readTree(recommendationContent);
			
			
			return  Recommendation.builder()
									.activityId(activity.getId())
									.userId(activity.getUserId())
									.activityType(activity.getType())
									.duration(activity.getDuration())
									.caloriesBurned(activity.getCaloriesBurned())
									.recommendation(recommendationNode.path(ANALYSIS_NODE).asText())
									.improvements(getImprovements(recommendationNode.path("improvements")))
									.suggestions(getSuggestion(recommendationNode.path("suggestions")))
									.safety(getSafety(recommendationNode.path("safety")))
									.createdAt(LocalDateTime.now())
									.build();
		} catch (Exception ex) {

			ex.printStackTrace();
			return createDefaultRecommendation(activity);
		}
	}
	
	//TODO: ADD some Generic details for improvement, suggestion, safety
	private Recommendation createDefaultRecommendation(Activity activity) {
		return  Recommendation.builder()
				.activityId(activity.getId())
				.userId(activity.getUserId())
				.activityType(activity.getType())
				.createdAt(LocalDateTime.now())
				.build();
	}

	private String processAiResponse(String aiRecommendationResponse) {
		try {
			
			JsonNode rootNode = mapper.readTree(aiRecommendationResponse);
			
			JsonNode textNode = rootNode.path("candidates")
										.get(0)
										.path("content")
										.path("parts")
										.get(0)
										.path("text");
			String recommendationContent = textNode.asText()
									.replaceAll("```json\\n", "")
									.replaceAll("\\n```", "")
									.replaceAll("\n", "")
									.trim();
			
			return recommendationContent;
		}catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	private String createPromptForActivity(Activity activity) {
		return String.format(PromptConstants.RECOMMENDATION_PROMPT, 
									activity.getType(), 
									activity.getDuration(), 
									activity.getCaloriesBurned(), 
									activity.getAdditionalMetrics());
	}
	
	private List<Recommendation.Improvements> getImprovements(JsonNode improvementsNode){
		List<Recommendation.Improvements> improvements = new ArrayList<>();
		improvementsNode.forEach(improv -> {
			Recommendation.Improvements improvement = Recommendation.Improvements.builder()
														.area(improv.path("area").asText())
														.recommendation(improv.path("recommendation").asText()).build();
			improvements.add(improvement);
		});
		return improvements;
	}
	
	private List<Recommendation.Suggestion> getSuggestion(JsonNode suggestionNode){
		List<Recommendation.Suggestion> suggestions = new ArrayList<>();
		suggestionNode.forEach(sugg -> {
			Recommendation.Suggestion suggestion = Recommendation.Suggestion.builder()
														.workout(sugg.path("workout").asText())
														.description(sugg.path("description").asText()).build();
			suggestions.add(suggestion);
		});
		return suggestions;
	}
	
	private List<String> getSafety(JsonNode safetyNode){
		
		return StreamSupport.stream(safetyNode.spliterator(), false)
			    .map(JsonNode::asText)
			    .collect(Collectors.toList());
	}

}

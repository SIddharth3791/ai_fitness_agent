package com.service.fitnessAIService.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.fitnessAIService.model.Recommendation;
import com.service.fitnessAIService.service.RecommendationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
	
	private final RecommendationService recommendationService;
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId){
		return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
	}
	
	@GetMapping("/activity/{activityId}")
	public ResponseEntity<List<Recommendation>> getActivityRecommendation(@PathVariable String activityId){
		return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
	}

}

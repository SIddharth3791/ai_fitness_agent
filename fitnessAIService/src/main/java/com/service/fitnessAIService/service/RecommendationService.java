package com.service.fitnessAIService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.fitnessAIService.model.Recommendation;
import com.service.fitnessAIService.repository.RecommendationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationService {
	
	private final RecommendationRepository recommendationRepository;
	
	public List<Recommendation> getUserRecommendation(String userId){
		return recommendationRepository.findByUserId(userId);
	}
	public Recommendation getActivityRecommendation(String activityId){
		return recommendationRepository.findByActivityId(activityId);
	}

}

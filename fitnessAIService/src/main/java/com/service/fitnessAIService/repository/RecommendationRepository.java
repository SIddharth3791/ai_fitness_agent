package com.service.fitnessAIService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.service.fitnessAIService.model.Recommendation;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {

	List<Recommendation> findByUserId(String userId);

	Recommendation findByActivityId(String activityId);

}

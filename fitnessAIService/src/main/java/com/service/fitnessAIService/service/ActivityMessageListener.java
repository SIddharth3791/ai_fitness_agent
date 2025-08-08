package com.service.fitnessAIService.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.service.fitnessAIService.model.Activity;
import com.service.fitnessAIService.model.Recommendation;
import com.service.fitnessAIService.repository.RecommendationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {
	
	private final ActivityAIService activityAIService;
	
	private final RecommendationRepository recommendationRepository;
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@RabbitListener(queues = "${rabbitmq.queue.name}")
	public void processActivity(Activity activity) {
		
		log.info("Received Message :: Processing Activity with ID {}", activity.getId());
		
		Recommendation aiRecommendation = activityAIService.generateRecommendation(activity);
		
		recommendationRepository.save(aiRecommendation);
		
	}

}

package com.service.fitnessAIService.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
	
	private String id;
	private String userId;
	private String type;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Map<String, Object> additionalMetrics;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}

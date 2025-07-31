package com.fitness.agent.activityService.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fitness.agent.activityService.model.ActivityType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {

	private String userId;
	private ActivityType type;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Map<String, Object> additionalMetrics;
	
}

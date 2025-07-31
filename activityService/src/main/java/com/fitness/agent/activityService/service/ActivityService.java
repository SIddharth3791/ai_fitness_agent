package com.fitness.agent.activityService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.fitness.agent.activityService.dto.ActivityRequest;
import com.fitness.agent.activityService.dto.ActivityResponse;
import com.fitness.agent.activityService.model.Activity;
import com.fitness.agent.activityService.reporsitory.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {
	
	private final ActivityRepository activityRepository;

	public ActivityResponse trackActivity(ActivityRequest request) {
		Activity savedActivity = activityRepository.save(mapActivityFromRequest(request));
		return mapActivityResponse(savedActivity);
	}
	
	public List<ActivityResponse> getUserActivities(String userId){
		List<Activity> activities = activityRepository.findByUserId(userId);
		
		return activities.stream().map(activity -> mapActivityResponse(activity)).toList();
	}
	
	public ActivityResponse getActivityById(String activityId){
		Optional<Activity> activity = activityRepository.findById(activityId);
		
		return activity.isPresent() ? mapActivityResponse(activity.get()) : null;
	}
	
	private Activity mapActivityFromRequest(ActivityRequest request) {
		return Activity.builder()
				.userId(request.getUserId())
				.type(request.getType())
				.duration(request.getDuration())
				.caloriesBurned(request.getCaloriesBurned())
				.startTime(request.getStartTime())
				.endTime(request.getEndTime())
				.additionalMetrics(request.getAdditionalMetrics())
				.build();
	}
	
	
	private ActivityResponse mapActivityResponse(Activity activity) {
		
		return ActivityResponse.builder().id(activity.getId())
				.userId(activity.getUserId())
				.type(activity.getType())
				.duration(activity.getDuration())
				.caloriesBurned(activity.getCaloriesBurned())
				.startTime(activity.getStartTime())
				.endTime(activity.getEndTime())
				.additionalMetrics(activity.getAdditionalMetrics())
				.createdAt(activity.getCreatedAt())
				.updatedAt(activity.getUpdatedAt())
				.build();
	}
}

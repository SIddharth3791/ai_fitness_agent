package com.fitness.agent.activityService.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.agent.activityService.dto.ActivityRequest;
import com.fitness.agent.activityService.dto.ActivityResponse;
import com.fitness.agent.activityService.service.ActivityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {
	
	private final ActivityService activityService;

	@PostMapping
	public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
		return ResponseEntity.ok(activityService.trackActivity(request));
	}
	
	@GetMapping()
	public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") String userId){
		return ResponseEntity.ok(activityService.getUserActivities(userId));
	}
	
	@GetMapping("/{activityId}")
	public ResponseEntity<ActivityResponse> getActivityById(@PathVariable String activityId){
		return ResponseEntity.ok(activityService.getActivityById(activityId));
	}
}

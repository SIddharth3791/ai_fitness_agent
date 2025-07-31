package com.fitness.agent.UserService.dto;

import java.time.LocalDateTime;

import com.fitness.agent.UserService.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	private String id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDateTime createedAt;
	private LocalDateTime updatedAt;

}

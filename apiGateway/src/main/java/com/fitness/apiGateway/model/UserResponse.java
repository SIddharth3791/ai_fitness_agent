package com.fitness.apiGateway.model;

import java.time.LocalDateTime;


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
	private String keycloakId;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDateTime createedAt;
	private LocalDateTime updatedAt;

}

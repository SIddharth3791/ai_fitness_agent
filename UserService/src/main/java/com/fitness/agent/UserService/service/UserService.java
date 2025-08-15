package com.fitness.agent.UserService.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fitness.agent.UserService.dto.RegisterRequest;
import com.fitness.agent.UserService.dto.UserResponse;
import com.fitness.agent.UserService.model.User;
import com.fitness.agent.UserService.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
	
	private UserRepository userRepository;
	
	public UserResponse register(RegisterRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			User existingUser = userRepository.findByEmail(request.getEmail());
			
			return UserResponse.builder().id(existingUser.getId())
					.keycloakId(existingUser.getKeycloakId())
					.email(existingUser.getEmail())
					.firstName(existingUser.getFirstName())
					.lastName(existingUser.getLastName())
					.createedAt(existingUser.getCreateedAt())
					.updatedAt(existingUser.getUpdatedAt()).build();
		}
		
		User user = User.builder().email(request.getEmail())
								.keycloakId(request.getKeycloakId())
								.firstName(request.getFirstName())
								.lastName(request.getLastName()).build();
		
		User savedUser = userRepository.save(user);
		
		return UserResponse.builder().id(savedUser.getId())
							.keycloakId(savedUser.getKeycloakId())
							.email(savedUser.getEmail())
							.firstName(savedUser.getFirstName())
							.lastName(savedUser.getLastName())
							.createedAt(savedUser.getCreateedAt())
							.updatedAt(savedUser.getUpdatedAt()).build();
	}
	
	public UserResponse getUserProfile(String userId) {
		
			Optional<User> userEntity = userRepository.findById(userId);
			
			if(userEntity.isPresent()) {
				User user = userEntity.get();
				
				return UserResponse.builder().id(user.getId()).email(user.getEmail())
						.firstName(user.getFirstName())
						.lastName(user.getLastName())
						.createedAt(user.getCreateedAt())
						.updatedAt(user.getUpdatedAt()).build();
				
			}else {
				throw new RuntimeException("User Doesnt Exist");
			}
	}

	public Boolean existByUserId(String keycloakId) {
		log.info("Calling User Validation for User ID {} ", keycloakId);
		return userRepository.existsByKeycloakId(keycloakId);
	}
		

}

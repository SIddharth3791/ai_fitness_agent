package com.fitness.agent.UserService.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fitness.agent.UserService.dto.RegisterRequest;
import com.fitness.agent.UserService.dto.UserResponse;
import com.fitness.agent.UserService.model.User;
import com.fitness.agent.UserService.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepository;
	
	public UserResponse register(RegisterRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email Already Exist");
		}
		
		User user = User.builder().email(request.getEmail())
								.password(request.getPassword())
								.firstName(request.getFirstName())
								.lastName(request.getLastName()).build();
		
		User savedUser = userRepository.save(user);
		
		return UserResponse.builder().id(savedUser.getId())
							.email(savedUser.getEmail())
							.password(savedUser.getPassword())
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
						.password(user.getPassword())
						.firstName(user.getFirstName())
						.lastName(user.getLastName())
						.createedAt(user.getCreateedAt())
						.updatedAt(user.getUpdatedAt()).build();
				
			}else {
				throw new RuntimeException("User Doesnt Exist");
			}
	}
		

}

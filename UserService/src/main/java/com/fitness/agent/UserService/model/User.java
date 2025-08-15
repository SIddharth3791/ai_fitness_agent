package com.fitness.agent.UserService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String keycloakId;
	
	@Column(unique = true, nullable = false)
	private String email;
	private String firstName;
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private UserRole role = UserRole.USER;
	
	@CreationTimestamp
	private LocalDateTime createedAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
}

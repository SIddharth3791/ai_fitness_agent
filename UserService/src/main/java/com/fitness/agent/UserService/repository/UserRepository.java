package com.fitness.agent.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.agent.UserService.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	boolean existsByEmail(String email);

	Boolean existsByKeycloakId(String keycloakId);

	User findByEmail(String email);

}

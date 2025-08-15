package com.fitness.apiGateway.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Builder
@Getter
@Setter
public class RegisterRequest {
	
	private String keycloakId;
	@NotBlank(message = "Email Is Required")
	@Email(message = "Invalid Email ")
	private String email;
	private String firstName;
	private String lastName;

}
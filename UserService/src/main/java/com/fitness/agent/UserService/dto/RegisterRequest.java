package com.fitness.agent.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
	
	@NotBlank(message = "Email Is Required")
	@Email(message = "Invalid Email ")
	private String email;
	
	@NotBlank(message = "Password Is Required")
	@Size(min = 6, message = "Password must have atleast 6 Characters")
	private String password;
	
	private String firstName;
	private String lastName;

}

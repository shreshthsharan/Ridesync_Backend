package com.ridesync.dto;

import com.ridesync.enums.UserRole;

import lombok.Data;

@Data
public class AuthResponseDTO {

	private String token;
	private UserRole role;
	private Long userId;
	
}

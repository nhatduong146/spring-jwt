package com.jwt.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String username;
	private String password;
	private String fullName;
	private int status;
	private List<String> roles;
}

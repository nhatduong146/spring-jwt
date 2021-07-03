package com.jwt.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jwt.dto.UserDTO;
import com.jwt.entity.RoleEntity;
import com.jwt.entity.UserEntity;

@Component
public class UserConvertor {
	public UserDTO toDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setFullName(userEntity.getFullName());
		
		List<String> roles = new ArrayList<>();
		for(RoleEntity role : userEntity.getRoles())
			roles.add(role.getCode());
		userDTO.setRoles(roles);
		
		return userDTO;
	}
}

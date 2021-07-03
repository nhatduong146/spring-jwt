package com.jwt.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.convertor.UserConvertor;
import com.jwt.dto.UserDTO;
import com.jwt.entity.UserEntity;
import com.jwt.model.CustomUserDetails;
import com.jwt.service.impl.UserService;

@RestController
public class UserAPI {
	@Autowired
	private UserConvertor userConvertor;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/profile")
	public ResponseEntity<Object> showProfile() {
		UserDTO userDTO = null;
		Object principle = 
				(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		UserEntity userEntity = userService.findById(userDetails.getUserEntity().getId());
		if(principle instanceof CustomUserDetails)
			userDTO = userConvertor.toDTO(((CustomUserDetails) principle).getUserEntity());
		return new ResponseEntity<Object>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping("/api/admin/users")
	public ResponseEntity<Object> getUsersInfo() {
		List<UserDTO> userDTOs = new ArrayList<>();
		for(UserEntity userEntity : userService.findAll())
		userDTOs.add(userConvertor.toDTO(userEntity));
		return new ResponseEntity<Object>(userDTOs, HttpStatus.OK);
	}
}

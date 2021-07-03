package com.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.entity.UserEntity;
import com.jwt.model.CustomUserDetails;
import com.jwt.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUsernameAndStatus(username, 1);
		if(userEntity == null)
			throw new UsernameNotFoundException("Username or password incorrect !!!");
		return new CustomUserDetails(userEntity);
	}
}

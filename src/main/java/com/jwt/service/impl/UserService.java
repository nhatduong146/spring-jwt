package com.jwt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.entity.UserEntity;
import com.jwt.repository.UserRepository;
import com.jwt.service.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity findById(Long id) {
		return userRepository.getById(id);
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}
	
}

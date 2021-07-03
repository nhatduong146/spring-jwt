package com.jwt.service;

import java.util.List;

import com.jwt.entity.UserEntity;

public interface IUserService {
	UserEntity findById(Long id);
	List<UserEntity> findAll();
}

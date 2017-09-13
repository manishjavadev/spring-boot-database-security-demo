package com.manish.javadev.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.manish.javadev.app.entities.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Long> {
	public abstract UserEntity findByUsername(String userName);
}
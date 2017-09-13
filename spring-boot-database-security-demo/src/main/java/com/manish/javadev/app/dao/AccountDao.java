package com.manish.javadev.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.manish.javadev.app.entities.AccountEntity;

public interface AccountDao extends CrudRepository<AccountEntity, Long> {

}

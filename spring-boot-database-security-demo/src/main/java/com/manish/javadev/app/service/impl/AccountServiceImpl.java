package com.manish.javadev.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manish.javadev.app.dao.AccountDao;
import com.manish.javadev.app.entities.AccountEntity;
import com.manish.javadev.app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public List<AccountEntity> findAllAccounts() {
		return (List<AccountEntity>) accountDao.findAll();
	}

	@Override
	public AccountEntity findAccountById(Long accId) {
		return accountDao.findOne(accId);
	}

	@Override
	public AccountEntity createAccount(AccountEntity accountEntity) {
		return accountDao.save(accountEntity);
	}

	@Override
	public void updateAccount(AccountEntity accountEntity) {
		accountDao.save(accountEntity);
	}

	@Override
	public void deleteAccount(long accId) {
		accountDao.delete(accId);
	}
}

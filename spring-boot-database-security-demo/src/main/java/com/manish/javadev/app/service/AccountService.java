package com.manish.javadev.app.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.manish.javadev.app.entities.AccountEntity;

public interface AccountService {

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	List<AccountEntity> findAllAccounts();

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	AccountEntity findAccountById(Long accId);

	@Secured({ "ROLE_ADMIN" })
	AccountEntity createAccount(AccountEntity accountEntity);

	@Secured({ "ROLE_ADMIN" })
	void updateAccount(AccountEntity accountEntity);

	@Secured({ "ROLE_ADMIN" })
	void deleteAccount(long accoId);
}

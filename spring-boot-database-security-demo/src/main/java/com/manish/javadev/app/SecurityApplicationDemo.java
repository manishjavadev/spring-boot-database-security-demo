package com.manish.javadev.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.manish.javadev.app.dao.AccountDao;
import com.manish.javadev.app.dao.UserDao;
import com.manish.javadev.app.entities.AccountEntity;
import com.manish.javadev.app.entities.RoleEntity;
import com.manish.javadev.app.entities.UserEntity;

@SpringBootApplication
public class SecurityApplicationDemo implements CommandLineRunner {
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplicationDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		saveAccountData();
	}

	private void saveAccountData() {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

		Set<RoleEntity> roles = new HashSet<RoleEntity>();
		RoleEntity roleEntity1 = new RoleEntity("ROLE_ADMIN");
		RoleEntity roleEntity2 = new RoleEntity("ROLE_USER");

		UserEntity userEntity1 = new UserEntity("msmanish95", bcpe.encode("P@ssw0rd"), true);
		userEntity1.getRoles().add(roleEntity1);
		userEntity1.getRoles().add(roleEntity2);
		roleEntity1.getUsers().add(userEntity1);
		roleEntity2.getUsers().add(userEntity1);

		UserEntity userEntity2 = new UserEntity("msmanish96", bcpe.encode("P@ssw0rd"), true);

		userEntity2.getRoles().add(roleEntity2);
		roleEntity2.getUsers().add(userEntity2);

		userDao.save(userEntity1);
		userDao.save(userEntity2);

		AccountEntity accountEntity = new AccountEntity("Saving Account", "Manish", new Double(20000));
		accountDao.save(accountEntity);

		accountEntity = new AccountEntity("Salary Account", "Divya", new Double(25000));
		accountDao.save(accountEntity);

		accountEntity = new AccountEntity("Personal Account", "Veena", new Double(30000));
		accountDao.save(accountEntity);

	}
}
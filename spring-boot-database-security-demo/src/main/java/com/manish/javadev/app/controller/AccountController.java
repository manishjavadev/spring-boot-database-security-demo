package com.manish.javadev.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.manish.javadev.app.entities.AccountEntity;
import com.manish.javadev.app.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/account/{accId}")
	public ResponseEntity<AccountEntity> getAccountById(@PathVariable("accId") Long accId) {
		AccountEntity accountEntity = accountService.findAccountById(accId);
		return new ResponseEntity<AccountEntity>(accountEntity, HttpStatus.OK);
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<AccountEntity>> getAllAccounts() {
		List<AccountEntity> accList = accountService.findAllAccounts();
		return new ResponseEntity<List<AccountEntity>>(accList, HttpStatus.OK);
	}

	@PostMapping("/account")
	public ResponseEntity<String> createAccount(@RequestBody AccountEntity accountEntity,
			UriComponentsBuilder builder) {
		AccountEntity resultEntity = accountService.createAccount(accountEntity);
		HttpHeaders headers = new HttpHeaders();
		URI resultPath = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accId}")
				.buildAndExpand(resultEntity.getAccountNumber()).toUri();
		headers.setLocation(resultPath);
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/account")
	public ResponseEntity<AccountEntity> updateAccount(@RequestBody AccountEntity accountEntity) {
		accountService.updateAccount(accountEntity);
		return new ResponseEntity<AccountEntity>(accountEntity, HttpStatus.OK);
	}

	@DeleteMapping("/account/{accId}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("accId") Long accId) {
		accountService.deleteAccount(accId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
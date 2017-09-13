package com.manish.javadev.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.manish.javadev.app.entities.AccountEntity;

public class AccountRestTemplateDemo {

	public static void main(String[] args) {
		testRestMethod();
	}

	private static void testRestMethod() {
		System.out.println("=====Find By Account Id========");
		findByAccountId();

		System.out.println("=====Find All Account========");
		findAllAccount();

		System.out.println("=====Create Account========");
		createAccount();

		System.out.println("=====Update Account========");
		updateAccount();

		System.out.println("=====Delete Account========");
		deleteAccount();
	}

	private static void findByAccountId() {
		HttpHeaders httpHeders = getHeaders();
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/api/account/{accId}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeders);
		ResponseEntity<AccountEntity> responseEntity = template.exchange(url, HttpMethod.GET, requestEntity,
				AccountEntity.class, new Long(2));
		System.out.println(responseEntity.getBody());

	}

	private static void createAccount() {

		HttpHeaders httpHeders = getHeaders();
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/api/account";
		AccountEntity accountEntity = new AccountEntity("Saving Account", "Manish New", new Double(20000));

		HttpEntity<AccountEntity> requestEntity = new HttpEntity<AccountEntity>(accountEntity, httpHeders);
		URI responseEntity = template.postForLocation(url, requestEntity);
		System.out.println(responseEntity.getPath());

	}

	private static void updateAccount() {
		HttpHeaders httpHeders = getHeaders();
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/api/account";
		AccountEntity accountEntity = new AccountEntity("Saving Account", "Manish New", new Double(20000));
		accountEntity.setAccountNumber(new Long(1));
		HttpEntity<AccountEntity> requestEntity = new HttpEntity<AccountEntity>(accountEntity, httpHeders);
		template.put(url, requestEntity);

	}

	private static void deleteAccount() {
		HttpHeaders httpHeders = getHeaders();
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/api/account/{accId}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeders);
		template.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, new Long(2));

	}

	private static void findAllAccount() {
		HttpHeaders httpHeders = getHeaders();
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/api/accounts";
		HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeders);
		ResponseEntity<AccountEntity[]> responseEntity = template.exchange(url, HttpMethod.GET, requestEntity,
				AccountEntity[].class);
		for (AccountEntity accountEntity : responseEntity.getBody()) {
			System.out.println(accountEntity);
		}

	}

	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}

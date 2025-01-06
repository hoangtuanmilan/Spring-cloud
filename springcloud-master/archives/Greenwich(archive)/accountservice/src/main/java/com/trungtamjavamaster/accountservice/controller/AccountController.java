package com.trungtamjavamaster.accountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjavamaster.accountservice.model.AccountDTO;
import com.trungtamjavamaster.accountservice.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;

	// THEM MOI
	@PostMapping("/account")
	public AccountDTO addAccount(@RequestBody AccountDTO accountDTO) {
		accountService.add(accountDTO);
		return accountDTO;
	}

	// get all
	@GetMapping("/accounts")
	public List<AccountDTO> getAll() {
		return accountService.getAll();
	}

	@GetMapping("/account/{id}")
	public AccountDTO get(@PathVariable(name = "id") Long id) {
		return accountService.getOne(id);
	}

	@DeleteMapping("/account/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		accountService.delete(id);
	}

	@PutMapping("/account")
	public void update(@RequestBody AccountDTO accountDTO) {
		accountService.update(accountDTO);
	}

}

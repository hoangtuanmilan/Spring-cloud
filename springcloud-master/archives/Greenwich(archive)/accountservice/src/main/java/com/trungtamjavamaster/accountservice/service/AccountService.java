package com.trungtamjavamaster.accountservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trungtamjavamaster.accountservice.client.ProductService;
import com.trungtamjavamaster.accountservice.dao.AccountDao;
import com.trungtamjavamaster.accountservice.entity.Account;
import com.trungtamjavamaster.accountservice.model.AccountDTO;

public interface AccountService {
	void add(AccountDTO accountDTO);

	void update(AccountDTO accountDTO);

	void updatePassword(AccountDTO accountDTO);

	void delete(Long id);

	List<AccountDTO> getAll();

	AccountDTO getOne(Long id);

}

@Transactional
@Service
class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private ProductService productService;

	@Override
	public void add(AccountDTO accountDTO) {
		Account account = new Account();
		account.setName(accountDTO.getName());
		account.setUsername(accountDTO.getUsername());
		account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
		account.setRole(accountDTO.getRole());
		accountDao.save(account);
		
		accountDTO.setId(account.getId());
	}

	@Override
	public void update(AccountDTO accountDTO) {
		Account account = accountDao.getOne(accountDTO.getId());
		if (account != null) {
			account.setName(accountDTO.getName());
			account.setUsername(accountDTO.getUsername());
			account.setRole(accountDTO.getRole());
			accountDao.save(account);
		}
	}

	@Override
	public void updatePassword(AccountDTO accountDTO) {
		Account account = accountDao.getOne(accountDTO.getId());
		if (account != null) {
			account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
			accountDao.save(account);
		}
	}

	@Override
	public void delete(Long id) {
		Account account = accountDao.getOne(id);
		if (account != null) {
			accountDao.delete(account);
		}
	}

	@Override
	public List<AccountDTO> getAll() {
		List<AccountDTO> accountDTOs = new ArrayList<>();

		accountDao.findAll().forEach((account) -> {
			accountDTOs.add(convert(account));
			//// PROduct
		});

		return accountDTOs;
	}

	@Override
	public AccountDTO getOne(Long id) {
		Account account = accountDao.getOne(id);
		if (account != null) {
			return convert(account);
		}

		return null;
	}

	private AccountDTO convert(Account account) {
		AccountDTO accountDTO = new AccountDTO();

		accountDTO.setId(account.getId());
		accountDTO.setName(account.getName());
		accountDTO.setUsername(account.getUsername());
		accountDTO.setRole(account.getRole());

		return accountDTO;
	}
}

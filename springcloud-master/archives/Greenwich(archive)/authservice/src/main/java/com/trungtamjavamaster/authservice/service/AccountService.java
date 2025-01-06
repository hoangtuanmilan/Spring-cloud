package com.trungtamjavamaster.authservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trungtamjavamaster.authservice.dao.AccountDao;
import com.trungtamjavamaster.authservice.entity.Account;

@Service
public class AccountService implements UserDetailsService {
	@Autowired
	private AccountDao accountDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountDao.getByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("no user");
		}
		
		return new User(account.getUsername(), account.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(account.getRole())));
	}

}

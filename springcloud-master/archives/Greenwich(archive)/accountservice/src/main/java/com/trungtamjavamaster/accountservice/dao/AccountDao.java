package com.trungtamjavamaster.accountservice.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trungtamjavamaster.accountservice.entity.Account;

@Transactional
@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

	
}

package com.trungtamjavamaster.authservice.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trungtamjavamaster.authservice.entity.Account;


@Transactional
@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
	@Query("SELECT a FROM Account a WHERE a.username = :username")
	Account getByUsername(@Param("username") String username);
}
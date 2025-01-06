package jmaster.io.authservice.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;

import jmaster.io.authservice.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}

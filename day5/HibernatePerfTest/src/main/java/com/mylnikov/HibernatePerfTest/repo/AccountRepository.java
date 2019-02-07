package com.mylnikov.HibernatePerfTest.repo;

import com.mylnikov.HibernatePerfTest.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

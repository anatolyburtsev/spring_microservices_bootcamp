package com.mylnikov.HibernatePerfTest.repo;

import com.mylnikov.HibernatePerfTest.entity.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SimpleUser, Long> {
}

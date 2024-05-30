package com.Demo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Demo.security.model.DBUser;


public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
	public DBUser findByUsername(String username);
}

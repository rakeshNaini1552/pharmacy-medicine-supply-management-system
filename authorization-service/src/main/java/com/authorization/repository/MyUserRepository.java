package com.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authorization.model.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String> {

	
}

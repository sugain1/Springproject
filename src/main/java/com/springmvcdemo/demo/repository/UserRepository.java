package com.springmvcdemo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmvcdemo.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User  findByUsernameAndPassword(String un, String psw);
}

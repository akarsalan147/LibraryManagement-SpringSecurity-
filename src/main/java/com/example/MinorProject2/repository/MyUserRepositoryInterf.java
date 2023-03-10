package com.example.MinorProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MinorProject2.model.MyUser;

public interface MyUserRepositoryInterf extends JpaRepository<MyUser, Integer>{

	MyUser findByUsername(String username);
	
}

package com.example.MinorProject2.requests;

import com.example.MinorProject2.model.Admin;
import com.example.MinorProject2.model.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

	private String username;
	private String password;
	private String authority;
	private Student student;
	private Admin admin;
	
}

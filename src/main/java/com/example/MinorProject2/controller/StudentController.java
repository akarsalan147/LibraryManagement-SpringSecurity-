package com.example.MinorProject2.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.spi.ValidationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MinorProject2.model.MyUser;
import com.example.MinorProject2.model.Student;
import com.example.MinorProject2.requests.StudentCreateRequest;
import com.example.MinorProject2.service.StudentServiceInterf;

@RestController
public class StudentController {

	@Autowired
	StudentServiceInterf studentServiceInterf;
	
	@PostMapping("/saveStudent")
	public ResponseEntity saveStudent(@Valid  @RequestBody StudentCreateRequest studentCreateRequest) {
		return new ResponseEntity(studentServiceInterf.save(studentCreateRequest),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/student")
	public Student getStudent() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyUser myUser = (MyUser) authentication.getPrincipal();
		
		if(myUser.getStudent() == null) {
			throw new Exception("User is not a student");
		}
		return studentServiceInterf.findById(myUser.getStudent().getId());
	}
}
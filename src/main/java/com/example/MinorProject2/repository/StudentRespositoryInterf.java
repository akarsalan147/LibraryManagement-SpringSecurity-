package com.example.MinorProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MinorProject2.model.Student;

public interface StudentRespositoryInterf extends JpaRepository<Student, Integer>{

}
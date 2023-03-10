package com.example.MinorProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MinorProject2.enums.TransactionType;
import com.example.MinorProject2.model.Book;
import com.example.MinorProject2.model.Student;
import com.example.MinorProject2.model.Transaction;

public interface TransactionRepositoryInterf extends JpaRepository<Transaction, Integer>{

	Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(Book book, Student student,
			TransactionType issue);

}
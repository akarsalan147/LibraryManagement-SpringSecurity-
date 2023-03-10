package com.example.MinorProject2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MinorProject2.enums.Genre;
import com.example.MinorProject2.model.Book;

public interface BookRepositoryInterf extends JpaRepository<Book, Integer>{
	
	List<Book> findByName(String name);
	List<Book> findByAuthor_name(String authorName);
	List<Book> findByGenre(Genre genre);
	List<Book> findByCost(int cost);
}
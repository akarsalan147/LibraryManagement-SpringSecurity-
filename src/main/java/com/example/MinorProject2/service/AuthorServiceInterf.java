package com.example.MinorProject2.service;

import com.example.MinorProject2.model.Author;

public interface AuthorServiceInterf {
	
	Author createAuthor(Author author);
	
	Author findByEmail(String email);

}
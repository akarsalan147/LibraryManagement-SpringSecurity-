package com.example.MinorProject2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MinorProject2.enums.BookFilterType;
import com.example.MinorProject2.enums.BookSearchOperationType;
import com.example.MinorProject2.model.Book;
import com.example.MinorProject2.requests.BookCreateRequest;
import com.example.MinorProject2.response.BookSearchResponse;
import com.example.MinorProject2.service.BookServiceInterf;

@RestController
public class BookController {
	
	@Autowired
	BookServiceInterf bookServiceInterf;
	
	@PostMapping("/saveBook")
	public ResponseEntity saveBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
		bookServiceInterf.create(bookCreateRequest);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public void deleteBookById(@PathVariable("id") int id) {
		bookServiceInterf.deleteBook(id);
	}
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookServiceInterf.getAllBooks();
	}
	
	
	@GetMapping("/books/search")
	public List<BookSearchResponse> findBooks(@RequestParam("filter") BookFilterType bookFilterType,
			@RequestParam("value") String value){
		return bookServiceInterf.findFilteredBooks(bookFilterType, value);
	}
	
	@GetMapping("/books/search/robust")
	public List<BookSearchResponse> findBooks(
			@RequestParam("filter") BookFilterType bookFilterType,
			@RequestParam("value") String value,
			@RequestParam("bookSearchOperationtype") BookSearchOperationType bookSearchOperationtype){
		return bookServiceInterf.findFilteredBooks(bookFilterType, value);
	}
	

}
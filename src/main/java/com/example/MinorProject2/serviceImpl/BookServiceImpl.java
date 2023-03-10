package com.example.MinorProject2.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MinorProject2.enums.BookFilterType;
import com.example.MinorProject2.enums.BookSearchOperationType;
import com.example.MinorProject2.enums.Genre;
import com.example.MinorProject2.model.Author;
import com.example.MinorProject2.model.Book;
import com.example.MinorProject2.repository.BookRepositoryInterf;
import com.example.MinorProject2.requests.BookCreateRequest;
import com.example.MinorProject2.response.BookSearchResponse;
import com.example.MinorProject2.service.AuthorServiceInterf;
import com.example.MinorProject2.service.BookServiceInterf;

@Service
public class BookServiceImpl implements BookServiceInterf {
	
	@Autowired
	BookRepositoryInterf bookRepositoryInterf;
	
	@Autowired
	AuthorServiceInterf authorServiceInterf;

	@Override
	public Book create(BookCreateRequest bookCreateRequest) {
		Book book = bookCreateRequest.toBook();
		
		Author author = book.getAuthor();
		
		Author authorfromDb = authorServiceInterf.findByEmail(author.getEmail());
				
		
		if(authorfromDb == null) {
			authorfromDb = authorServiceInterf.createAuthor(author);

		}
		book.setAuthor(authorfromDb);
		
		return bookRepositoryInterf.save(book);

	}

	@Override
	public Book save(Book book) {
		return bookRepositoryInterf.save(book);
	}

	@Override
	public Book findById(Integer id) {
		return bookRepositoryInterf.findById(id).get();
	}
	
	public List<BookSearchResponse> findFilteredBooks(BookFilterType bookFilterType, String value){
		return find(bookFilterType, value).stream()
				.map(book -> book.toBookSearchResponse())
					.collect(Collectors.toList());
	}

	public List<Book> find(BookFilterType bookFilterType, String value) {
		switch(bookFilterType) {
		case NAME:
			return bookRepositoryInterf.findByName(value);
		case GENRE:
			return bookRepositoryInterf.findByGenre(Genre.valueOf(value));
		case AUTHOR_NAME:
			return bookRepositoryInterf.findByAuthor_name(value);
		case COST:
			return bookRepositoryInterf.findByCost(Integer.parseInt(value));
		case ID:
			return bookRepositoryInterf.findAllById(new ArrayList<Integer>(Integer.parseInt(value)));
		}
		return null;
	}

	@Override
	public List<BookSearchResponse> findRobustFilteredBooks(BookFilterType bookFilterType, String value,
			BookSearchOperationType bookSearchOperationType) {
		// TODO 
		return null;
	}

	@Override
	public void deleteBook(int id) {
		bookRepositoryInterf.deleteById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepositoryInterf.findAll();
	}

}
package com.example.MinorProject2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.MinorProject2.repository.AuthorRepositoryInterf;

@SpringBootApplication
public class MinorProject2Application implements CommandLineRunner{
	
	@Autowired
	AuthorRepositoryInterf authorRepositoryInterf;

	public static void main(String[] args) {
		SpringApplication.run(MinorProject2Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inside Run");
		
//		List<Author> authorList = authorRepositoryInterf.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(10,"India","p");
//		authorList.forEach(System.out::println);
	}

}

package com.example.MinorProject2.service;

import com.example.MinorProject2.exception.TransactionServiceException;

public interface TransactionServiceInterf {
	
	String issueTxn(int studentId,int bookId) throws TransactionServiceException;
	String returntxn(int studentId, int BookId) throws TransactionServiceException;
}
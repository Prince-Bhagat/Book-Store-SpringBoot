package com.pictlibrary.dev.service;

import com.pictlibrary.dev.model.Book;
import com.pictlibrary.dev.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

	public Iterable<Book> getAllBook() {
		return bookRepository.findAll();
	}

	public Book addBook(Book entity) {
		return bookRepository.save(entity);
	}

	public Iterable<Book> searchBook(String key) {
		return bookRepository.findAllByBookNameContaining(key);
	}
}

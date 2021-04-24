package com.pictlibrary.dev.repository;

import com.pictlibrary.dev.model.Book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Integer>{

	@Query("SELECT b FROM Book b WHERE b.bookName LIKE %?1%")
	Iterable<Book> findAllByBookNameContaining(String key);


}

package com.kumar.books_rest_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kumar.books_rest_api.entity.Books;

@Service
public interface BooksService {

   List<Books> getAllBooksEntities();

   Books getBookByIdEntity(Long id);

   Books saveBookEntity(Books book);

   Books updateBookEntity(Long id, Books book);

   void deleteBookById(Long id);

   void deleteAllBooks();
}

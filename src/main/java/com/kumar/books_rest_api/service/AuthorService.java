package com.kumar.books_rest_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kumar.books_rest_api.entity.Author;

@Service
public interface AuthorService {
    List<Author> getAllAuthorsEntities();

    Author getAuthorByIdEntity(Long id);

    Author saveAuthorEntity(Author author);

    Author updateAuthorEntity(Long id, Author updatedAuthor);

    void deleteAuthorById(Long id);

    void deleteAllAuthors();
}

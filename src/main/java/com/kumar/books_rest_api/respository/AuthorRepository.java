package com.kumar.books_rest_api.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.books_rest_api.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // List<Author> findByGetAllAuthorsEntities();

    // Author GetAuthorByIdEntity(Long id);

    // Author saveAuthorEntity(Author author);

    // Author updateAuthorEntity(Long id, Author author);

    // void deleteAuthorById(Long id);

    // void deleteAllAuthors();

}

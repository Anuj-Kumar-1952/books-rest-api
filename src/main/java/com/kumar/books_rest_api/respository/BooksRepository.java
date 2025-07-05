package com.kumar.books_rest_api.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.books_rest_api.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {
    // List<Books> getAllBooksEntities();

    // Books getBookByIdEntity(Long id);

    // Books saveBookEntity(Books book);

    // Books updateBookEntity(Long id, Books book);

    // void deleteBookById(Long id);

    // void deleteAllBooks();

}

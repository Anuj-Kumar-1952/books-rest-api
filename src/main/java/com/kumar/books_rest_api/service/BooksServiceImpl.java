package com.kumar.books_rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumar.books_rest_api.entity.Books;
import com.kumar.books_rest_api.exception.ResourceNotFoundException;
import com.kumar.books_rest_api.respository.BooksRepository;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    // @Autowired
    // private AuthorService authorService; // Needed to fetch authors by ID

    @Override
    public List<Books> getAllBooksEntities() {
        return booksRepository.findAll();
    }

    @Override
    public Books getBookByIdEntity(Long id) {
        return booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
    }

    @Override
    public Books saveBookEntity(Books book) {
        // Note: at this point, the controller has already attached authors
        return booksRepository.save(book);
    }

    @Override
    public Books updateBookEntity(Long id, Books updatedBook) {
        Books existingBook = booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setAuthors(updatedBook.getAuthors());

        return booksRepository.save(existingBook);
    }

    @Override
    public void deleteBookById(Long id) {
        if (!booksRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with ID: " + id);
        }
        booksRepository.deleteById(id);
    }

    @Override
    public void deleteAllBooks() {
        booksRepository.deleteAll();
    }
}

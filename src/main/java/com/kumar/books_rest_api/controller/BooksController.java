package com.kumar.books_rest_api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.books_rest_api.dto.AuthorsSummaryDTO;
import com.kumar.books_rest_api.dto.BookDetailsDTO;
import com.kumar.books_rest_api.dto.BookRequestDTO;
import com.kumar.books_rest_api.dto.BooksSummaryDTO;
import com.kumar.books_rest_api.entity.Author;
import com.kumar.books_rest_api.entity.Books;
import com.kumar.books_rest_api.service.AuthorService;
import com.kumar.books_rest_api.service.BooksService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BooksController {

        @Autowired
        private BooksService booksService;

        @Autowired
        private AuthorService authorService;

        @GetMapping
        public ResponseEntity<List<BooksSummaryDTO>> getAllBooks() {
                List<Books> books = booksService.getAllBooksEntities();
                List<BooksSummaryDTO> bookDTOs = books.stream()
                                .map(book -> new BooksSummaryDTO(book.getBook_id(), book.getTitle()))
                                .collect(Collectors.toList());
                return ResponseEntity.ok(bookDTOs);
        }

        @GetMapping("/{id}")
        public ResponseEntity<BookDetailsDTO> getBookById(@PathVariable Long id) {
                Books book = booksService.getBookByIdEntity(id);

                List<AuthorsSummaryDTO> authorDTOs = book.getAuthors().stream()
                                .map(author -> new AuthorsSummaryDTO(author.getAuthor_id(), author.getName()))
                                .collect(Collectors.toList());

                BookDetailsDTO bookDTO = new BookDetailsDTO(
                                book.getBook_id(),
                                book.getTitle(),
                                book.getIsbn(),
                                book.getPrice(),
                                authorDTOs);

                return ResponseEntity.ok(bookDTO);
        }

        @PostMapping
        public ResponseEntity<BookDetailsDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequest) {
                // Manually map DTO → Entity
                Books book = new Books();
                book.setTitle(bookRequest.getTitle());
                book.setIsbn(bookRequest.getIsbn());
                book.setPrice(bookRequest.getPrice());

                // Fetch and attach authors
                List<Author> authors = bookRequest.getAuthorIds().stream()
                                .map(authorService::getAuthorByIdEntity) // use existing service to fetch Author
                                .collect(Collectors.toList());

                book.setAuthors(authors);

                Books savedBook = booksService.saveBookEntity(book);

                List<AuthorsSummaryDTO> authorDTOs = savedBook.getAuthors().stream()
                                .map(author -> new AuthorsSummaryDTO(author.getAuthor_id(), author.getName()))
                                .collect(Collectors.toList());

                BookDetailsDTO bookDTO = new BookDetailsDTO(
                                savedBook.getBook_id(),
                                savedBook.getTitle(),
                                savedBook.getIsbn(),
                                savedBook.getPrice(),
                                authorDTOs);

                return ResponseEntity.status(HttpStatus.CREATED).body(bookDTO);
        }

        @PutMapping("/{id}")
        public ResponseEntity<BookDetailsDTO> updateBook(@PathVariable Long id,
                        @Valid @RequestBody BookRequestDTO bookRequest) {
                // Map DTO → Entity
                Books book = new Books();
                book.setTitle(bookRequest.getTitle());
                book.setIsbn(bookRequest.getIsbn());
                book.setPrice(bookRequest.getPrice());

                // Fetch and attach authors
                List<Author> authors = bookRequest.getAuthorIds().stream()
                                .map(authorService::getAuthorByIdEntity)
                                .collect(Collectors.toList());

                book.setAuthors(authors);

                Books updatedBook = booksService.updateBookEntity(id, book);

                List<AuthorsSummaryDTO> authorDTOs = updatedBook.getAuthors().stream()
                                .map(author -> new AuthorsSummaryDTO(author.getAuthor_id(), author.getName()))
                                .collect(Collectors.toList());

                BookDetailsDTO bookDTO = new BookDetailsDTO(
                                updatedBook.getBook_id(),
                                updatedBook.getTitle(),
                                updatedBook.getIsbn(),
                                updatedBook.getPrice(),
                                authorDTOs);

                return ResponseEntity.ok(bookDTO);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
                booksService.deleteBookById(id);
                return ResponseEntity.noContent().build();
        }

        @DeleteMapping
        public ResponseEntity<Void> deleteAllBooks() {
                booksService.deleteAllBooks();
                return ResponseEntity.noContent().build();
        }
}

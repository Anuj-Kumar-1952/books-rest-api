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

import com.kumar.books_rest_api.dto.AuthorDetailsDTO;
import com.kumar.books_rest_api.dto.AuthorRequestDTO;
import com.kumar.books_rest_api.dto.AuthorsSummaryDTO;
import com.kumar.books_rest_api.dto.BooksSummaryDTO;
import com.kumar.books_rest_api.entity.Author;
import com.kumar.books_rest_api.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorsSummaryDTO>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthorsEntities();
        List<AuthorsSummaryDTO> authorDTOs = authors.stream()
                .map(author -> new AuthorsSummaryDTO(author.getAuthor_id(), author.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDetailsDTO> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorByIdEntity(id);

        List<BooksSummaryDTO> bookDTOs = author.getBooks().stream()
                .map(book -> new BooksSummaryDTO(book.getBook_id(), book.getTitle()))
                .collect(Collectors.toList());

        AuthorDetailsDTO authorDTO = new AuthorDetailsDTO(
                author.getAuthor_id(),
                author.getName(),
                author.getBio(),
                bookDTOs);

        return ResponseEntity.ok(authorDTO);
    }

    @PostMapping
    public ResponseEntity<AuthorDetailsDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorRequest) {
        // Manually map DTO → Entity
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setBio(authorRequest.getBio());

        Author savedAuthor = authorService.saveAuthorEntity(author);

        List<BooksSummaryDTO> bookDTOs = savedAuthor.getBooks().stream()
                .map(book -> new BooksSummaryDTO(book.getBook_id(), book.getTitle()))
                .collect(Collectors.toList());

        AuthorDetailsDTO authorDTO = new AuthorDetailsDTO(
                savedAuthor.getAuthor_id(),
                savedAuthor.getName(),
                savedAuthor.getBio(),
                bookDTOs);

        return ResponseEntity.status(HttpStatus.CREATED).body(authorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDetailsDTO> updateAuthor(@PathVariable Long id,
            @Valid @RequestBody AuthorRequestDTO authorRequest) {
        // Map DTO → Entity
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setBio(authorRequest.getBio());

        Author updatedAuthor = authorService.updateAuthorEntity(id, author);

        List<BooksSummaryDTO> bookDTOs = updatedAuthor.getBooks().stream()
                .map(book -> new BooksSummaryDTO(book.getBook_id(), book.getTitle()))
                .collect(Collectors.toList());

        AuthorDetailsDTO authorDTO = new AuthorDetailsDTO(
                updatedAuthor.getAuthor_id(),
                updatedAuthor.getName(),
                updatedAuthor.getBio(),
                bookDTOs);

        return ResponseEntity.ok(authorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAuthors() {
        authorService.deleteAllAuthors();
        return ResponseEntity.noContent().build();
    }
}

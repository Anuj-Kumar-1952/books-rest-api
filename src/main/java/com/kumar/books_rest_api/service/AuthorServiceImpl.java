package com.kumar.books_rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumar.books_rest_api.entity.Author;
import com.kumar.books_rest_api.exception.ResourceNotFoundException;
import com.kumar.books_rest_api.respository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthorsEntities() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByIdEntity(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + id));
    }

    @Override
    public Author saveAuthorEntity(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthorEntity(Long id, Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + id));

        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setBio(updatedAuthor.getBio());

        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with ID: " + id);
        }
        authorRepository.deleteById(id);
    }

    @Override
    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }

}

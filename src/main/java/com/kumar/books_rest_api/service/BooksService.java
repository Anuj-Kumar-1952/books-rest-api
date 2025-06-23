package com.kumar.books_rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kumar.books_rest_api.entity.Books;
import com.kumar.books_rest_api.respository.BooksRepository;

@Service
public class BooksService {
   @Autowired
   BooksRepository br;

   public ResponseEntity<List<Books>> getAllBooks() {
      List<Books> books = (List<Books>) br.findAll();
      try {
         if (books.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
      } catch (Exception e) {
         ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
         e.getMessage();
      }
      return ResponseEntity.of(Optional.of(books));
   }

   public ResponseEntity<Books> getBooksById(int id) {
      Books b = br.findById(id);
      try {
         if (b.getAuthor().isEmpty()) {
            return ResponseEntity.ok(b);
         }
      } catch (Exception e) {
         ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         e.getMessage();
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
   }

   public ResponseEntity<Books> saveBooks(Books b) {
      Books savedBook = null;
      try {
         if (b == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         }
         savedBook = br.save(b);
      } catch (Exception e) {
         // log the error message
         System.err.println("Error while saving book: " + e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(null);
      }
      return ResponseEntity.ok(savedBook);
   }

   public ResponseEntity<Books> updateBook(int id,Books updatedBook){
       try {
        Books b= br.findById(id);

        if (b.equals(null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Ensure the ID from path is used
        updatedBook.setBook_id(id);
        br.save(updatedBook);
        
      } catch (Exception e) {
         System.err.println("Update error: " + e.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
      return ResponseEntity.ok(updatedBook);
   }

   public ResponseEntity<Void> deleteBookById(int id){
       try {
          int i=br.deleteById(id);
          if (i<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
          }
       } catch (Exception e) {
         ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      e.getMessage();   
      }
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   public ResponseEntity<Void> deleteAll(){
     List<Books> books = (List<Books>) br.findAll();
      try {
         if (books.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
         }
         br.deleteAll(books);
      } catch (Exception e) {
         ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
         e.getMessage();
      }
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}

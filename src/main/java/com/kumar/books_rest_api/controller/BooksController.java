package com.kumar.books_rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.books_rest_api.entity.Books;
import com.kumar.books_rest_api.service.BooksService;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class BooksController {
    @RequestMapping("/demo")
    public String requestMethodName() {
        return new String("this is for demo purpose ");
    }

    @Autowired
    private BooksService bs;

    @GetMapping("/allbooks")
    public ResponseEntity<List<Books>> getAllBook() {
        return bs.getAllBooks();
    }

    @GetMapping("/book/{book_id}")
    public ResponseEntity<Books> getMethodName(@PathVariable("book_id") int id) {
        return bs.getBooksById(id);
    }

    @PostMapping("/addbook")
    public ResponseEntity<Books> postSaveBook(@RequestBody Books b) {
        return bs.saveBooks(b);
    }
    
    @PutMapping("/updatebook/{id}")
    public ResponseEntity<Books> putUpdateBook(@PathVariable("id") int id, @RequestBody Books b) {
        return bs.updateBook(id,b);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id")int id){
        return bs.deleteBookById(id);
    }

     @DeleteMapping("/deleteall")
    public ResponseEntity<Void> deleteAllBook(){
        return bs.deleteAll();
    }
}

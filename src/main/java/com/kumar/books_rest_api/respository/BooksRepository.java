package com.kumar.books_rest_api.respository;

import org.springframework.data.repository.CrudRepository;

import com.kumar.books_rest_api.entity.Books;

public interface BooksRepository extends CrudRepository<Books,Integer>{
    public Books findById(int book_id);
    public int deleteById(int id);
}

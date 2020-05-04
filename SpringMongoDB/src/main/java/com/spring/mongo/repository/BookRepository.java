package com.spring.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.mongo.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
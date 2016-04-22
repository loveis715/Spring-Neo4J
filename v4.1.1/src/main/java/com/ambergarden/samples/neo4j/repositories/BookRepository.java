package com.ambergarden.samples.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.ambergarden.samples.neo4j.entities.Book;

/**
 * The repository to perform CRUD operations on book entities
 */
public interface BookRepository extends GraphRepository<Book> {
}
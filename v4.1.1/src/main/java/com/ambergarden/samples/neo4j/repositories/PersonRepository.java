package com.ambergarden.samples.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.ambergarden.samples.neo4j.entities.Person;

/**
 * The repository to perform CRUD operations on person entities
 */
public interface PersonRepository extends GraphRepository<Person> {
}
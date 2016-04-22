package com.ambergarden.samples.neo4j.entities;

import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.ambergarden.samples.neo4j.entities.base.NamedEntity;

/**
 * Entity which represents a person
 */
@NodeEntity
public class Person extends NamedEntity {

   @Relationship(type="READER_OF")
   private Set<Book> books;

   public Set<Book> getBooks() {
      return books;
   }

   public void setBooks(Set<Book> books) {
      this.books = books;
   }
}
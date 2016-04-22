package com.ambergarden.samples.neo4j.entities;

import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.ambergarden.samples.neo4j.entities.base.NamedEntity;
import com.ambergarden.samples.neo4j.entities.relationships.WriterOf;

/**
 * Entity which represents a person
 */
@NodeEntity
public class Person extends NamedEntity {
   @Relationship(type="WRITER_OF")
   private Set<WriterOf> writings;

   @Relationship(type="READER_OF")
   private Set<Book> books;

   public Set<WriterOf> getWritings() {
      return writings;
   }

   public void setWritings(Set<WriterOf> writings) {
      this.writings = writings;
   }

   public Set<Book> getBooks() {
      return books;
   }

   public void setBooks(Set<Book> books) {
      this.books = books;
   }
}
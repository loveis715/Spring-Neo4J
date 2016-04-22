package com.ambergarden.samples.neo4j.entities;

import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.ambergarden.samples.neo4j.entities.base.DescriptiveEntity;

/**
 * Entity which represents a book
 */
@NodeEntity
public class Book extends DescriptiveEntity {
   @Relationship(type="READER_OF", direction=Relationship.INCOMING)
   private Set<Person> readers;

   public Set<Person> getReaders() {
      return readers;
   }

   public void setReaders(Set<Person> readers) {
      this.readers = readers;
   }
}
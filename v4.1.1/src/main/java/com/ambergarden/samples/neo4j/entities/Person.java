package com.ambergarden.samples.neo4j.entities;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Entity which represents a person
 */
@NodeEntity
public class Person {
   @GraphId
   private Long id;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
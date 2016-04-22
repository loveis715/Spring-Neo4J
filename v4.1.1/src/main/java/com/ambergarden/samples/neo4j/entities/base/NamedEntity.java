package com.ambergarden.samples.neo4j.entities.base;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * The abstract entity which represents an entity with a name
 */
@NodeEntity
public abstract class NamedEntity extends AbstractEntity {
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
package com.ambergarden.samples.neo4j.entities.base;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * The abstract entity which represents an entity with a description
 */
@NodeEntity
public abstract class DescriptiveEntity extends NamedEntity {
   private String description;

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
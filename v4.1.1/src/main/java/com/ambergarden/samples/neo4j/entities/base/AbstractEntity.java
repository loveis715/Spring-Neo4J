package com.ambergarden.samples.neo4j.entities.base;

import org.neo4j.ogm.annotation.GraphId;

/**
 * The abstract entity which contains the basic fields such
 * as id, hashCode and equals function
 */
public abstract class AbstractEntity {

   @GraphId
   private Long id;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }

      if (obj == null) {
         return false;
      }

      if (getClass() != obj.getClass()) {
         return false;
      }

      if (this.id == null) {
         // For newly created entity, id will be null
         return false;
      }

      AbstractEntity entity = (AbstractEntity) obj;
      return this.id.equals(entity.id);
   }

   @Override
   public int hashCode() {
      return id == null ? super.hashCode() : id.hashCode();
   }
}
package com.ambergarden.samples.neo4j.entities;

import org.neo4j.ogm.annotation.NodeEntity;

import com.ambergarden.samples.neo4j.entities.base.NamedEntity;

/**
 * Entity which represents a person
 */
@NodeEntity
public class Person extends NamedEntity {
}
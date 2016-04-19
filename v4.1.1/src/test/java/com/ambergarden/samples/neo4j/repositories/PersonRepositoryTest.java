package com.ambergarden.samples.neo4j.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ambergarden.samples.neo4j.entities.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/spring/dal-test-context.xml" })
public class PersonRepositoryTest {
   @Autowired
   private PersonRepository personRepository;

   @Test
   public void testCRUDPerson() {
      Person person = new Person();
      person = personRepository.save(person);
      assertNotNull(person);
      assertNotNull(person.getId());

      Long personId = person.getId();
      personRepository.delete(person);
      person = personRepository.findOne(personId);
      assertNull(person);
   }
}
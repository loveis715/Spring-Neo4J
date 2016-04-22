package com.ambergarden.samples.neo4j.repositories;

import static org.junit.Assert.assertEquals;
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
   private static String TEST_PERSON_NAME_1 = "Person1";
   private static String TEST_PERSON_NAME_2 = "Person2";

   @Autowired
   private PersonRepository personRepository;

   @Test
   public void testCRUDPerson() {
      Person person = new Person();
      person.setName(TEST_PERSON_NAME_1);
      person = personRepository.save(person);
      assertNotNull(person);
      assertNotNull(person.getId());
      assertEquals(TEST_PERSON_NAME_1, person.getName());

      Long originalId = person.getId();
      person.setName(TEST_PERSON_NAME_2);
      person = personRepository.save(person);
      assertEquals(originalId, person.getId());
      assertEquals(TEST_PERSON_NAME_2, person.getName());

      personRepository.delete(person);
      person = personRepository.findOne(originalId);
      assertNull(person);
   }
}
package com.ambergarden.samples.neo4j.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ambergarden.samples.neo4j.entities.Book;
import com.ambergarden.samples.neo4j.entities.Person;
import com.ambergarden.samples.neo4j.entities.relationships.WriterOf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/spring/dal-test-context.xml" })
public class BookRepositoryTest {
   private static String TEST_PERSON_NAME_1 = "Person1";

   private static String TEST_BOOK_NAME_1 = "Book1";
   private static String TEST_BOOK_NAME_2 = "Book2";

   @Autowired
   private PersonRepository personRepository;

   @Autowired
   private BookRepository bookRepository;

   @Test
   public void testCRUDBook() {
      Book book = new Book();
      book.setName(TEST_BOOK_NAME_1);
      book = bookRepository.save(book);
      assertNotNull(book);
      assertNotNull(book.getId());
      assertEquals(TEST_BOOK_NAME_1, book.getName());

      Long originalId = book.getId();
      book.setName(TEST_BOOK_NAME_2);
      book = bookRepository.save(book);
      assertEquals(originalId, book.getId());
      assertEquals(TEST_BOOK_NAME_2, book.getName());

      bookRepository.delete(book);
      book = bookRepository.findOne(originalId);
      assertNull(book);
   }

   @Test
   public void testCRUDRelationships() {
      Person person1 = new Person();
      person1.setName(TEST_PERSON_NAME_1);
      person1 = personRepository.save(person1);

      // Test create with readers
      Set<Person> readers = new HashSet<Person>();
      readers.add(person1);

      Set<Book> books = new HashSet<Book>();
      Book book1 = new Book();
      books.add(book1);
      person1.setBooks(books);

      book1.setName(TEST_BOOK_NAME_1);
      book1.setReaders(readers);
      book1 = bookRepository.save(book1);
      assertNotNull(book1);
      assertNotNull(book1.getReaders());
      assertEquals(1, book1.getReaders().size());

      // Test add readers
      Book book2 = new Book();
      book2.setName(TEST_BOOK_NAME_2);
      book2 = bookRepository.save(book2);
      assertNotNull(book2);
      assertNull(book2.getReaders());

      readers = new HashSet<Person>();
      readers.add(person1);
      person1.getBooks().add(book2);
      book2.setReaders(readers);
      book2 = bookRepository.save(book2);
      assertNotNull(book2.getReaders());
      assertEquals(1, book2.getReaders().size());

      // Verify that the person entity is in sync
      person1 = personRepository.findOne(person1.getId());
      assertNotNull(person1.getBooks());
      assertEquals(2, person1.getBooks().size());

      // Test remove readers
      book2.setReaders(null);
      person1.getBooks().remove(book2);
      book2 = bookRepository.save(book2);

      person1 = personRepository.findOne(person1.getId());
      assertNotNull(person1.getBooks());
      assertEquals(1, person1.getBooks().size());

      // Test remove all readers
      book1.setReaders(null);
      person1.getBooks().remove(book1);
      book1 = bookRepository.save(book1);

      person1 = personRepository.findOne(person1.getId());
      assertNull(person1.getBooks());
   }

   @Test
   public void testRichRelationship() {
      Date timestamp = new Date();
      Person person1 = new Person();
      person1.setName(TEST_PERSON_NAME_1);
      person1 = personRepository.save(person1);

      // Test create with writers
      Book book1 = new Book();
      Set<WriterOf> writings = new HashSet<WriterOf>();
      person1.setWritings(writings);

      WriterOf writer = new WriterOf();
      writer.setBook(book1);
      writer.setWriter(person1);
      writer.setStartDate(timestamp);
      writer.setEndDate(timestamp);
      writings.add(writer);

      book1.setName(TEST_BOOK_NAME_1);
      book1.setWriters(writings);
      book1 = bookRepository.save(book1);
      assertNotNull(book1);
      assertNotNull(book1.getWriters());
      assertEquals(1, book1.getWriters().size());

      for (WriterOf writerOf : book1.getWriters()) {
         assertNotNull(writerOf.getStartDate());
         assertNotNull(writerOf.getEndDate());
      }
   }

   @Test
   public void testCRUDRelationshipEntities() {
      Person person1 = new Person();
      person1.setName(TEST_PERSON_NAME_1);
      person1 = personRepository.save(person1);

      // Test create with writers
      Book book1 = new Book();
      Set<WriterOf> writings = new HashSet<WriterOf>();
      person1.setWritings(writings);

      WriterOf writer = new WriterOf();
      writer.setBook(book1);
      writer.setWriter(person1);
      writings.add(writer);

      book1.setName(TEST_BOOK_NAME_1);
      book1.setWriters(writings);
      book1 = bookRepository.save(book1);
      assertNotNull(book1);
      assertNotNull(book1.getWriters());
      assertEquals(1, book1.getWriters().size());

      // Test add readers
      Book book2 = new Book();
      book2.setName(TEST_BOOK_NAME_2);
      book2 = bookRepository.save(book2);
      assertNotNull(book2);
      assertNull(book2.getReaders());

      writings = new HashSet<WriterOf>();
      writer = new WriterOf();
      writer.setBook(book2);
      writer.setWriter(person1);
      writings.add(writer);

      book2.setWriters(writings);
      book2 = bookRepository.save(book2);
      assertNotNull(book2.getWriters());
      assertEquals(1, book2.getWriters().size());

      // Verify that the person entity is in sync
      person1 = personRepository.findOne(person1.getId());
      assertNotNull(person1.getWritings());
      assertEquals(2, person1.getWritings().size());

      // Test remove writers
      person1.getWritings().removeAll(book1.getWriters());
      person1.getWritings().removeAll(book2.getWriters());
      book2.setWriters(null);
      book2 = bookRepository.save(book2);
      book1.setWriters(null);
      book1 = bookRepository.save(book1);

      person1 = personRepository.findOne(person1.getId());
      assertNull(person1.getWritings());
   }
}
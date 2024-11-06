package com.BookHive.backend.services;

import com.BookHive.backend.entities.Author;
import com.BookHive.backend.entities.Book;
import com.BookHive.backend.repositories.AuthorRepository;
import com.BookHive.backend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
 
    //Αναθέτουμε τα BookRepository και AuthorRepository στην κλαση BookService για να μπορούμε να χρησιμοποιούμε τις μεθόδους τους 
    //για CRUD λειτουργίες 
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository; 

    // Δημιουργία βιβλίου
    public Book createBook(Book book) {
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if (authorOptional.isPresent()) {
            book.setAuthor(authorOptional.get());  
            return bookRepository.save(book); // Αποθήκευση του βιβλίου
        } else {
            throw new RuntimeException("Author not found with id: " + book.getAuthor().getId());
        }
    }

    // Εύρεση βιβλίου με βάση το id
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id); // Εύρεση βιβλίου με βάση το ID
    }

    // Λίστα όλων των βιβλίων
    public List<Book> getAllBooks() {
        return bookRepository.findAll(); // Λίστα βιβλίων
    }

    // Ενημέρωση βιβλίου
    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setIsbn(bookDetails.getIsbn());
    
            Author author = book.getAuthor(); // Παίρνουμε τον τρέχοντα συγγραφέα
            author.setName(bookDetails.getAuthor().getName()); // Ενημερώνουμε το όνομα
            author.setSurname(bookDetails.getAuthor().getSurname()); // Ενημερώνουμε το επώνυμο
    
            authorRepository.save(author); // Αποθηκεύουμε τις αλλαγές στον συγγραφέα
            return bookRepository.save(book); // Αποθηκεύουμε το ενημερωμένο βιβλίο
        }
        return null; // Αν το βιβλίο δεν βρεθεί
    }

    // Διαγραφή βιβλίου
    public void deleteBook(Long id) {
        bookRepository.deleteById(id); // χρήση της CRUD JPA μεθόδου deleteByID για διαγραφή βιβλίου
    }
}

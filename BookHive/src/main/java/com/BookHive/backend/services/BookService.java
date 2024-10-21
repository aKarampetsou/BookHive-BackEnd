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

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository; // Κάνε inject το AuthorRepository εδώ

    // Δημιουργία βιβλίου
    public Book createBook(Book book) {
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if (authorOptional.isPresent()) {
            book.setAuthor(authorOptional.get());  
            return bookRepository.save(book);  // Αποθήκευση του βιβλίου
        } else {
            throw new RuntimeException("Author not found with id: " + book.getAuthor().getId());
        }
    }
    
    
    // Εύρεση βιβλίου με βάση το id
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Λίστα όλων των βιβλίων
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Ενημέρωση βιβλίου
    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            return bookRepository.save(book);
        }
        return null; 
    }

    // Διαγραφή βιβλίου
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

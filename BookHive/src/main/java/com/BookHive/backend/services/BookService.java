package com.BookHive.backend.services;

import com.BookHive.backend.entities.Book;
import com.BookHive.backend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Δημιουργία βιβλίου
    public Book createBook(Book book) {
        return bookRepository.save(book);
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
        return null; // ή μπορείς να πετάξεις exception
    }

    // Διαγραφή βιβλίου
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

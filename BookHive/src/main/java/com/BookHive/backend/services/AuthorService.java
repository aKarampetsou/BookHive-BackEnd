package com.BookHive.backend.services;

import java.util.List;
import java.util.Optional;
import com.BookHive.backend.entities.Author;
import com.BookHive.backend.entities.Book;
import com.BookHive.backend.repositories.AuthorRepository;
import com.BookHive.backend.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // Επιστρέφει όλους τους συγγραφείς από τη βάση δεδομένων
    public List<Author> getAllAuthors() {
        return authorRepository.findAll(); // Λίστα συγγραφέων
    }

    // Επιστρέφει έναν συγγραφέα με βάση το ID
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id); // Εύρεση συγγραφέα με βάση το ID
    }

    // Δημιουργεί έναν νέο συγγραφέα στη βάση δεδομένων
    public Author createAuthor(Author author) {
        return authorRepository.save(author); // Αποθήκευση νέου συγγραφέα
    }

    // Διαγράφει έναν συγγραφέα με βάση το ID
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id); // Διαγραφή συγγραφέα
    }

    // Ενημερώνει τα στοιχεία ενός συγγραφέα
    public Author updateAuthor(Long id, Author authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setName(authorDetails.getName());
            author.setSurname(authorDetails.getSurname());
            return authorRepository.save(author); // Ενημέρωση και αποθήκευση συγγραφέα
        } 
        return null;  // Αν ο συγγραφέας δεν βρεθεί
    }

    // Επιστρέφει τα βιβλία ενός συγγραφέα
    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId); // Εύρεση βιβλίων συγγραφέα
    }

    // Προσθέτει ένα βιβλίο σε έναν συγγραφέα
    public Book addBookToAuthor(Long authorId, Book book) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            book.setAuthor(author);
            return bookRepository.save(book); // Αποθήκευση βιβλίου
        } else {
            throw new RuntimeException("Author not found with id: " + authorId);
        }
    }

    // Μέθοδος για αναζήτηση συγγραφέα με βάση το όνομα και το επώνυμο
    public Author getAuthorByNameAndSurname(String name, String surname) {
        return authorRepository.findByNameAndSurname(name, surname); // Αναζήτηση συγγραφέα
    }
}
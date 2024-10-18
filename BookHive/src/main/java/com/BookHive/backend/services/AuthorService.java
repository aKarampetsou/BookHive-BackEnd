package com.BookHive.backend.services;

import java.util.List;
import java.util.Optional;
import com.BookHive.backend.entities.Author;
import com.BookHive.backend.entities.Book;
import com.BookHive.backend.repositories.AuthorRepository;
import com.BookHive.backend.repositories.BookRepository; // Εισαγωγή του BookRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;  // Δήλωση και αυτόματο dependency injection για το BookRepository

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    // Μέθοδος για την ενημέρωση υπάρχοντος συγγραφέα
    public Author updateAuthor(Long id, Author authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(id); // Εύρεση του συγγραφέα με το συγκεκριμένο ID

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get(); // Λήψη του υπάρχοντος συγγραφέα

            // Ενημέρωση των πεδίων του συγγραφέα
            author.setName(authorDetails.getName());
            author.setSurname(authorDetails.getSurname());

            // Αποθήκευση του ενημερωμένου συγγραφέα στη βάση δεδομένων
            return authorRepository.save(author);
        } 
        return null; // Επιστροφή null αν ο συγγραφέας δεν βρεθεί
    }

    // Νέα μέθοδος για την εύρεση των βιβλίων ενός συγγραφέα
    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);  // Χρησιμοποιούμε το bookRepository για να βρούμε τα βιβλία του συγγραφέα
    }
}

package com.BookHive.backend.services;

import java.util.List;
import java.util.Optional;
import com.BookHive.backend.entities.Author;
import com.BookHive.backend.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

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

    //μέθοδος για την ενημέρωση υπάρχοντος συγγραφέα
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
}

package com.BookHive.backend.controllers;

import com.BookHive.backend.entities.Author; // Εισαγωγή της οντότητας Authors
import com.BookHive.backend.services.AuthorService; // Εισαγωγή της υπηρεσίας AuthorsService
import org.springframework.beans.factory.annotation.Autowired; // Εισαγωγή του Autowired
import org.springframework.http.HttpStatus; // Εισαγωγή του HttpStatus
import org.springframework.http.ResponseEntity; // Εισαγωγή του ResponseEntity
import org.springframework.web.bind.annotation.*; // Εισαγωγή των HTTP αναφορών

import java.util.List; // Εισαγωγή της λίστας
import java.util.Optional; // Εισαγωγή του Optional

@RestController // Κάνει την κλάση αυτή έναν RESTful controller
@RequestMapping("/api/authors") // Ορισμός του base URL για τους συγγραφείς
public class AuthorController {

    private final AuthorService authorsService; // Δημιουργία μεταβλητής για το AuthorsService

    @Autowired // Αυτόματο dependency injection για το AuthorsService
    public AuthorController(AuthorService authorsService) {
        this.authorsService = authorsService; // Αρχικοποίηση της υπηρεσίας
    }

    // Endpoint για την εύρεση όλων των συγγραφέων
    @GetMapping // GET /api/authors
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorsService.getAllAuthors(); // Εύρεση όλων των συγγραφέων
        return new ResponseEntity<>(authors, HttpStatus.OK); // Επιστροφή λίστας συγγραφέων με status 200
    }

    // Endpoint για την εύρεση συγγραφέα με βάση το ID
    @GetMapping("/{id}") // GET /api/authors/{id}
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorsService.getAuthorById(id); // Εύρεση συγγραφέα με το ID
        return author.map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // Αν βρεθεί, επιστρέφει τον συγγραφέα
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Αλλιώς, επιστρέφει 404
    }

    // Endpoint για τη δημιουργία νέου συγγραφέα
    @PostMapping // POST /api/authors
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author newAuthor = authorsService.createAuthor(author); // Δημιουργία νέου συγγραφέα
        return new ResponseEntity<>(newAuthor, HttpStatus.CREATED); // Επιστροφή του νέου συγγραφέα με status 201
    }

    // Endpoint για την ενημέρωση υπάρχοντος συγγραφέα
    @PutMapping("/{id}") // PUT /api/authors/{id}
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author updatedAuthor = authorsService.updateAuthor(id, authorDetails); // Ενημέρωση του συγγραφέα
        return updatedAuthor != null ? new ResponseEntity<>(updatedAuthor, HttpStatus.OK) // Αν ενημερωθεί, επιστρέφει τον συγγραφέα
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Αλλιώς, επιστρέφει 404
    }

    // Endpoint για τη διαγραφή συγγραφέα με το συγκεκριμένο ID
    @DeleteMapping("/{id}") // DELETE /api/authors/{id}
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorsService.deleteAuthor(id); // Διαγραφή του συγγραφέα
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Επιστροφή status 204
    }
}
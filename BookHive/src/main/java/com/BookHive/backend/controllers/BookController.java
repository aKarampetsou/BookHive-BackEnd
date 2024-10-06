package com.BookHive.backend.controllers;

import com.BookHive.backend.entities.Book;
import com.BookHive.backend.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books") // Ορίζουμε το βασικό endpoint για books
public class BookController {

    @Autowired
    private BookService bookService;

    // Endpoint για τη δημιουργία νέου βιβλίου
    @PostMapping // POST /api/books
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookService.createBook(book); // Κλήση της μεθόδου για δημιουργία βιβλίου
        return new ResponseEntity<>(newBook, HttpStatus.CREATED); // ο server επιστρέφει 201 για να δείξει ότι το βιβλίο δημιουργήθηκε επιτυχώς
    }

    // Endpoint για την επιστροφή όλων των βιβλίων
    @GetMapping // GET /api/books
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks(); // Κλήση της μεθόδου για εύρεση όλων των βιβλίων
        return new ResponseEntity<>(books, HttpStatus.OK); // ο server επιστρέφει τη λίστα βιβλίων με status 200
    }

    // Endpoint για την επιστροφή ενός βιβλίου με βάση το ID
    @GetMapping("/{id}") // GET /api/books/{id}
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id); // Κλήση της μεθόδου για εύρεση βιβλίου με το ID
        return book.isPresent() ? new ResponseEntity<>(book.get(), HttpStatus.OK) // Επιστροφή του βιβλίου αν βρεθεί
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // ο server επιστρέφει 404 για να δείξει ότι το βιβλίο δεν βρέθηκε
    }

    // Endpoint για την ενημέρωση υπάρχοντος βιβλίου
    @PutMapping("/{id}") // PUT /api/books/{id}
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails); // Κλήση της μεθόδου για ενημέρωση βιβλίου
        return updatedBook != null ? new ResponseEntity<>(updatedBook, HttpStatus.OK) // Επιστροφή του ενημερωμένου βιβλίου
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // ο server επιστρέφει 404 για να δείξει ότι το βιβλίο δεν βρέθηκε
    }

    // Endpoint για τη διαγραφή βιβλίου
    @DeleteMapping("/{id}") // DELETE /api/books/{id}
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id); // Κλήση της μεθόδου για διαγραφή βιβλίου
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // server επιστρέφει 204 για να δείξει ότι το βιβλίο διαγράφηκε επιτυχώς
    }
}

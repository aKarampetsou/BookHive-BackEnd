package com.BookHive.backend.controllers;

import com.BookHive.backend.dto.BookAuthorDTO;
import com.BookHive.backend.entities.Author;
import com.BookHive.backend.entities.Book;
import com.BookHive.backend.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.BookHive.backend.services.AuthorService;
import com.BookHive.backend.repositories.BookRepository;
import java.util.List;
import java.util.Optional;

//BookController = restful controller, διαχειριση αιτημάτων που αφορούν βιβλία. παρέχει endpoints για crud λειτουργίες, επιστρέφει JSON 
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/books") // Ορίζουμε το βασικό endpoint για books

public class BookController {
 
    /*To @Autowired επιτρέπει στο controller να χρησιμοποιεί τις υπηρεσίες
     BookService και AuthorService, καθώς και το BookRepository για διαχείριση βιβλίων και συγγραφέων*/
     
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService; 
    @Autowired
    private BookRepository bookRepository; 

    // Endpoint για τη δημιουργία νέου βιβλίου
    @PostMapping // POST /api/books
    public ResponseEntity<Book> createBook(@RequestBody Book book) { //Το νέο βιβλίο γίνεται JSON μέσω του RequestBody
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
        return (book.isPresent()) ? (new ResponseEntity<>(book.get(), HttpStatus.OK)) // Επιστροφή του βιβλίου αν βρεθεί
                                 : (new ResponseEntity<>(HttpStatus.NOT_FOUND)); // ο server επιστρέφει 404 για να δείξει ότι το βιβλίο δεν βρέθηκε
    }

    // Endpoint για την ενημέρωση υπάρχοντος βιβλίου
    @PutMapping("/{id}") // PUT /api/books/{id}
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
    Book updatedBook = bookService.updateBook(id, bookDetails); // Κλήση της μεθόδου για ενημέρωση βιβλίου
    return (updatedBook != null) ? ( new ResponseEntity<>(updatedBook, HttpStatus.OK)) // Επιστροφή του ενημερωμένου βιβλίου
                                 :  (new ResponseEntity<>(HttpStatus.NOT_FOUND)); // ο server επιστρέφει 404 για να δείξει ότι το βιβλίο δεν βρέθηκε
}


    // Endpoint για τη διαγραφή βιβλίου
    @DeleteMapping("/{id}") // DELETE /api/books/{id}
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id); // Κλήση της μεθόδου για διαγραφή βιβλίου
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // server επιστρέφει 204 για να δείξει ότι το βιβλίο διαγράφηκε επιτυχώς
    }

    // Endpoint για την προσθήκη βιβλίου και συγγραφέα ταυτόχρονα
    @PostMapping("/addBookWithAuthor")
    public ResponseEntity<String> addBookWithAuthor(@RequestBody BookAuthorDTO bookAuthorDTO) { //η παράμετρος BookAuthorDTO γίνεται JSON 
        try {
            // Έλεγχος αν το βιβλίο υπάρχει ήδη
            boolean exists = bookRepository.existsByTitleAndAuthorAndIsbn(
                bookAuthorDTO.getTitle(),
                bookAuthorDTO.getAuthorName(),
                bookAuthorDTO.getAuthorSurname(),
                bookAuthorDTO.getIsbn()
            );
            
            if (exists) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This book already exists.");
            }
    
            //κώδικας για δημιουργία συγγραφέα και βιβλίου
            Author author = authorService.getAuthorByNameAndSurname(bookAuthorDTO.getAuthorName(), bookAuthorDTO.getAuthorSurname());
            if (author == null) {
                author = new Author(bookAuthorDTO.getAuthorName(), bookAuthorDTO.getAuthorSurname());
                authorService.createAuthor(author);
            }
    
            Book book = new Book(bookAuthorDTO.getTitle(), bookAuthorDTO.getIsbn(), author);
            bookService.createBook(book);
    
            return new ResponseEntity<>("Book and Author added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

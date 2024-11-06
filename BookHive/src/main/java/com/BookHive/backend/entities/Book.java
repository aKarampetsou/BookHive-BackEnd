package com.BookHive.backend.entities;

import jakarta.persistence.*; //για να χρησιμοποιούμε JPA annotations
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; //για αποφυγή loop στα JSON


@Entity
@Table(name = "books", uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "author_id"})}) // κάθε συνδυασμός τίτλου και συγγραφέα είναι μοναδικός
public class Book {

    @Id // το id είναι το πρωτεύον κλειδί του πίνακα books
    @GeneratedValue(strategy = GenerationType.IDENTITY)// το id είναι auto increment 
    private Long id;

    @Column(name = "title", nullable = false)//Δημιουργείται η στήλη title που ΔΕΝ μπορεί να είναι null
    private String title;

    @Column(name = "isbn", nullable = false, unique = true, length = 13) 
    private String isbn;

    @ManyToOne(fetch = FetchType.EAGER) //Σχέση ενα προς πολλά με την οντότητα Author 
    @JoinColumn(name = "author_id", nullable = false)
   // @JsonBackReference // Χρησιμοποιείται για να μην έχουμε loop στο display του get αιτήματος
    @JsonIgnoreProperties("books") // Αγνοούμε τα βιβλία στον συγγραφέα για αποφυγή αναδρομής
    private Author author;


    //κενός constructor απαραίτητος για το JPA για να αρχικοποιήσει το entity 
    public Book() { 
    }

    //Constructor με ορίσματα για αρχικοποίηση των πεδίων 
    public Book(String title, String isbn, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
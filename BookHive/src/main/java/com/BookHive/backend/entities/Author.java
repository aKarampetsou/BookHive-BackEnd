package com.BookHive.backend.entities;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity // Δηλώνεται ότι η κλάση Authors είναι μια οντότητα που αντιστοιχεί σε έναν πίνακα της ΒΔ
@Table(name = "authors", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "surname"})}) /* Ο συνδυασμός όνομα-επώνυμο είναι μοναδικός για κάθε συγγραφέα */
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id 
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("author") // Αγνοούμε τον συγγραφέα στα βιβλία
    private List<Book> books; // με το cascade delete εάν διαγραφεί ένας συγγραφέας, διαγράφονται και τα βιβλία του 

    // Constructors
    public Author() { // Constructor χωρίς ορίσματα απαραίτητος για το JPA
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

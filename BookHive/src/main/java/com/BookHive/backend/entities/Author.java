package com.BookHive.backend.entities;

import jakarta.persistence.*;


@Entity //Δηλώνεται ότι η κλάση Authors είναι μια οντότητα που ανιτστοιχεί σε έναν πίνακα της ΒΔ
@Table(name = "authors", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "surname"})}) /*Ο συνδυασμός όνομα-επώνυμο είναι μοναδικός για κάθε συγγραφέα*/
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-increment id 
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    // Constructors
    public Author() { //constructor χωρίς ορίσματα απαραίτητος για το JPA
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
}

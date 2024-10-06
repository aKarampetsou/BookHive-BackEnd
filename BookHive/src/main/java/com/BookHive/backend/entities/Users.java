package com.BookHive.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

    // Ορίζει το πεδίο id ως το πρωτεύον κλειδί (primary key) της οντότητας
    @Id 
    
    // Αυτόματη δημιουργία τιμών για το πεδίο id (κάθε φορά που δημιουργείται μια νέα εγγραφή)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false, unique = true)  // Δηλώνει ότι το πεδίο "username" δεν μπορεί να είναι null και πρέπει να είναι μοναδικό (unique)
    private String username;

    @Column(nullable = false)
    private String password;

    // Constructors
    public Users() {  // Default constructor (απαραίτητος από το JPA για τη δημιουργία κενών αντικειμένων)
    }

    // Constructor για τη δημιουργία νέων χρηστών με username και password
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

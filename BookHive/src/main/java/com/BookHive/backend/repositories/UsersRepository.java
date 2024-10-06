package com.BookHive.backend.repositories;

import com.BookHive.backend.entities.Users; // Εισαγωγη της οτνοτητας Users
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Users findByUsername(String username); // Μέθοδος που βρίσκει εναν user από το username του
}

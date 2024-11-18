package com.BookHive.backend.repositories;

import com.BookHive.backend.entities.Users; // Εισαγωγη της οτνοτητας Users
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username); // Επιστροφή Optional
}

package com.BookHive.backend.repositories;

import com.BookHive.backend.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByNameAndSurname(String name, String surname);
}
package com.BookHive.backend.repositories;

import com.BookHive.backend.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @Query("SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname")
    Optional<Author> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}

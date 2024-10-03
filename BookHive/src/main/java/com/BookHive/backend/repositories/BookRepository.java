package com.BookHive.backend.repositories;

import com.BookHive.backend.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Να θυμηθώ να προσθέσω custom queries εδώ αν χρειαστεί
}

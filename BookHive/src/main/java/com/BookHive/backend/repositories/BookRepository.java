package com.BookHive.backend.repositories;

import java.util.List;
import com.BookHive.backend.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Μέθοδος για την εύρεση βιβλίων με βάση το author_id
    List<Book> findByAuthorId(Long authorId);
}

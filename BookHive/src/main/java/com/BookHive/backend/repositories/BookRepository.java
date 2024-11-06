package com.BookHive.backend.repositories;

import java.util.List;
import com.BookHive.backend.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository; //import το JPA repo για CRUD μεθόδους
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> { 

    //Custom μέθοδος για την εύρεση βιβλίων με βάση το author_id
    List<Book> findByAuthorId(Long authorId);

    // Custom μέθοδος και custom query για έλεγχο αν υπάρχει τουάχιστον ένα  βιβλίο με τα συγκεκριμένα στοιχεία
    @Query("SELECT COUNT(b) > 0 FROM Book b WHERE b.title = :title AND b.author.name = :authorName AND b.author.surname = :authorSurname AND b.isbn = :isbn")
    boolean existsByTitleAndAuthorAndIsbn(@Param("title") String title, @Param("authorName") String authorName, @Param("authorSurname") String authorSurname, @Param("isbn") String isbn);
}


package com.BookHive.backend.repositories;

import com.BookHive.backend.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> { //extends entity, type of primary key=long

    // Να θυμηθώ να προσθέσω custom queries εδώ αν χρειαστεί
}

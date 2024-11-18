package com.BookHive.backend.services;

import com.BookHive.backend.entities.Users; 
import com.BookHive.backend.repositories.UsersRepository; 
import org.springframework.beans.factory.annotation.Autowired; // Εισαγωγή του bean annotation (ex: @service)
import org.springframework.stereotype.Service; // Εισαγωγή του Service annotation
import java.util.List; 
import java.util.Optional; 

@Service // Κλάση υπηρεσίας
public class UsersService {

    private final UsersRepository usersRepository; // Δημιουργία μεταβλητής για το UsersRepository

    @Autowired // Αυτόματο dependency injection άρα δεν χρειάζεται να δημιουργήσω με το χέρι το αντικέιμενο BookRepository
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository; // Αρχικοποίηση του repository
    }

    // Μέθοδος για την εύρεση όλων των χρηστών
    public List<Users> findAllUsers() {
        return usersRepository.findAll(); // Επιστρέφει τη λίστα των χρηστών
    }

    // Μέθοδος για την εύρεση χρήστη με βάση το ID
    public Optional<Users> findUserById(Long id) {
        return usersRepository.findById(id); // Επιστρέφει τον χρήστη αν βρεθεί
    }
   
    //Μέθοδος για την εύρεση χρήστη με βάση το username 
    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username); // Επιστρέφει Optional<Users>
    }

    // Μέθοδος για τη δημιουργία νέου χρήστη
    public Users createUser(Users user) {
        return usersRepository.save(user); // Αποθηκεύει τον νέο χρήστη και τον επιστρέφει
    }

    // Μέθοδος για την ενημέρωση υπάρχοντος χρήστη
    public Users updateUser(Long id, Users userDetails) {
        Optional<Users> optionalUser = usersRepository.findById(id); // Βρίσκει τον χρήστη με το συγκεκριμένο ID
        if (optionalUser.isPresent()) { // Αν ο χρήστης βρεθεί
            Users user = optionalUser.get();
            user.setUsername(userDetails.getUsername()); // Ενημέρωση του username
            user.setPassword(userDetails.getPassword()); // Ενημέρωση του password
            return usersRepository.save(user); // Επιστρέφει τον ενημερωμένο χρήστη
        }
        return null; // Αλλιώς, αν ο χρήστης δεν βρεθέι επιστρέφει null 
    }

    // Μέθοδος για τη διαγραφή χρήστη με το συγκεκριμένο id
    public void deleteUser(Long id) {
        usersRepository.deleteById(id); 
    }
    
}

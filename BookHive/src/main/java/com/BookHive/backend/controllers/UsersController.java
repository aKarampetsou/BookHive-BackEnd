package com.BookHive.backend.controllers;

import com.BookHive.backend.entities.Users;
import com.BookHive.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Δηλώνεται ότι η κλάδη αυτή ένα RESTful controller και συνεπώς ό,τι επιστρέφεται από τα endpoints θα είναι JSON

@RequestMapping("/api/users") // Όλες οι διαδρομές αυτού του controller ξεκινούν από /api/users
public class UsersController {

    private final UsersService usersService; // Αναφορά στο service file που χειρίζεται τις λειτουργίες των χρηστών

    @Autowired // Αυτόματο dependency injection για το UsersService
    public UsersController(UsersService usersService) {
        this.usersService = usersService; // Αρχικοποίηση της υπηρεσίας
    }

    // Endpoint για να επιστρέφει όλους τους χρήστες
    @GetMapping("/all") // GET αίτημα στη διαδρομή /api/users/all
    public List<Users> findAllUsers() {
        return usersService.findAllUsers(); // Κλήση της μεθόδου που βρίσκεται στο UsersService file για να βρει όλους τους χρήστες
    }

    // Endpoint για να επιστρέφει έναν χρήστη με βάση το ID του
    @GetMapping("/{id}") // GET αίτημα στη διαδρομή /api/users/{id}, όπου {id} είναι το ID του χρήστη
    public Optional<Users> findUserById(@PathVariable Long id) {
        return usersService.findUserById(id); // Κλήση της μεθόδου της υπηρεσίας για να βρει έναν χρήστη με βάση το ID
    }

    // Endpoint για να δημιουργεί έναν νέο χρήστη
    @PostMapping("/create") // POST αίτημα στη διαδρομή /api/users/create
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user); // Κλήση της μεθόδου που βρίσκεται στο UsersService file για να δημιουργήσει έναν νέο χρήστη
    }

    // Endpoint για να ενημερώνει έναν υπάρχοντα χρήστη
    @PutMapping("/update/{id}") // PUT αίτημα στη διαδρομή /api/users/update/{id}
    public Users updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        return usersService.updateUser(id, userDetails); // Κλήση της μεθόδου που βρίσκεται στο UsersService file για να ενημερώσει τον χρήστη
    }

    // Endpoint για να διαγράφει έναν χρήστη με βάση το ID του
    @DeleteMapping("/delete/{id}") // DELETE αίτημα στη διαδρομή /api/users/delete/{id}
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id); // Κλήση της μεθόδου που βρίσκεται στο UsersService file για να διαγράψει έναν χρήστη
    }
}

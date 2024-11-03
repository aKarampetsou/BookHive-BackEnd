package com.BookHive.backend.controllers;

import com.BookHive.backend.entities.Users;
import com.BookHive.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//Ρύθμιση endpoint /auth/login που χειρίζεται αιτήματα για σύνδεση χρηστών
// το σύστημα συγκρίνει τον κωδικό που εισάγει ο χρήστης με τον αποθηκευμένο κωδικό πρόσβασης στη βάση δεδομένων
//Αν ταιριάζουν:Επιστρέφεται μήνυμα επιτυχίας και κωδικός 200 OK
//Αν δεν ταιριάζουν, επιστρέφεται μήνυμα αποτυχίας με 401 Unauthorized
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Users user) {
        Users foundUser = usersService.findByUsername(user.getUsername());
        
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.OK); // Επιτυχής σύνδεση
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Αποτυχία σύνδεσης
        }
    }
}

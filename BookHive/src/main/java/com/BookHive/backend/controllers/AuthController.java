package com.BookHive.backend.controllers;

import com.BookHive.backend.util.JwtService;
import com.BookHive.backend.repositories.UsersRepository;
import com.BookHive.backend.entities.Users;
import com.BookHive.backend.dto.LoginRequest;
import com.BookHive.backend.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") // Επιτρέπει αιτήματα από το React app
public class AuthController {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
       public LoginResponse login(@RequestBody LoginRequest request) {
       Optional<Users> user = userRepository.findByUsername(request.getUsername());
         if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
           String token = jwtService.generateToken(request.getUsername());
            return new LoginResponse(token); // Επιστροφή με σωστό κλειδί
        } else {
        throw new RuntimeException("Invalid credentials");
     }
   }
}

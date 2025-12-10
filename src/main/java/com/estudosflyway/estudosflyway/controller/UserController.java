package com.estudosflyway.estudosflyway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudosflyway.estudosflyway.controller.dtos.UserCreationDTO;
import com.estudosflyway.estudosflyway.model.User;
import com.estudosflyway.estudosflyway.service.UserService;

import org.springframework.lang.NonNull;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Injeção de Dependência do Service
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // --- GET ALL: /api/users ---
    //uri: http://localhost:8080/api/users
    @GetMapping 
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users); // Status 200 OK
        } catch (RuntimeException e) {
            // Lógica para capturar a exceção "Nenhum usuário encontrado"
            return ResponseEntity.status(HttpStatus.OK).build(); // Status 404 Not Found
        }
    }

    // --- GET BY ID: /api/users/{id} ---
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            if(user.isPresent()) {
                return ResponseEntity.ok(user.get()); // Status 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Status 404 Not Found
            }
        } catch (RuntimeException e) {
            // Lógica para capturar a exceção "Usuário não encontrado"
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Status 404 Not Found
        }
    }
    
    // --- POST (CREATE): /api/users ---
    @PostMapping 
    public ResponseEntity<User> createUser(@NonNull @RequestBody UserCreationDTO dto) {

        var user = new User();
        BeanUtils.copyProperties(dto, user); 
        
        try {
            User createdUser = userService.createUser(user); 
            // Status 201 Created é o padrão para criação bem-sucedida
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); 
        } catch (IllegalArgumentException e) {
            // Captura a exceção de negócio do Service (ex: ID fornecido)
            return ResponseEntity.badRequest().body(null); // Status 400 Bad Request
        } catch (Exception e) {
            // Captura "Usuário já existe"
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Status 400 Bad Request
        }
    }

    // --- PUT (UPDATE): /api/users/{id} ---
    //uri: http://localhost:8080/api/users/1
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        // Garantindo que o ID do path e o ID do corpo são usados corretamente
        if (!id.equals(userDetails.getId())) {
             return ResponseEntity.badRequest().body(null); // Status 400 se IDs não corresponderem
        }
        
        try {
            User updatedUser = userService.updateUser(userDetails);
            return ResponseEntity.ok(updatedUser); // Status 200 OK
        } catch (IllegalArgumentException e) {
            // Captura ID null
            return ResponseEntity.badRequest().build(); // Status 400 Bad Request
        } catch (RuntimeException e) {
            // Captura "Usuário não encontrado"
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Status 404 Not Found
        }
    }
}

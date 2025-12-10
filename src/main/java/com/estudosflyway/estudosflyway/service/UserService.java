package com.estudosflyway.estudosflyway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.estudosflyway.estudosflyway.model.User;
import com.estudosflyway.estudosflyway.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Retorna todos os usuários
    public List<User> getAllUsers() {
        // Lógica de negócios adicionais (ex: validar email único) pode vir aqui.
        List<User> users = userRepository.findAll();
        return users;
    }

    //Cria um novo usuário
    public User createUser(User user) {
        validateNewUser(user);
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Usuário já existe");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já existe");
        }
        return userRepository.save(user);
    }

    //Retorna um usuário pelo id
    public Optional<User> getUserById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório para buscar um usuário.");
        }
        return userRepository.findById(id);
    }

    //Atualiza um usuário
    public User updateUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório para atualização.");
        }
        
        // 1. Busca e verifica a existência em UMA chamada (reutilizando getUserById)
        Optional<User> existingUser = getUserById(user.getId()); 
        
        // 2. Transfere/Atualiza os dados da entidade de entrada (user) para a entidade gerenciada (existingUser)
        if(existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword()); 
            userToUpdate.setRole(user.getRole());
            return userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    private void validateNewUser(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("ID não deve ser enviado ao criar um novo usuário.");
        }
    
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
    
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
    
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }
    }

}

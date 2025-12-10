package com.estudosflyway.estudosflyway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudosflyway.estudosflyway.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username); // Voce ultiliza Optional quando voce quer retornar um unico objeto ou null
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username); // Voce ultiliza boolean quando voce quer verificar se um objeto existe ou nao
    boolean existsByEmail(String email); 
}

package com.estudosflyway.estudosflyway.model;

import java.time.LocalDateTime;
import java.util.List;

import com.estudosflyway.estudosflyway.controller.dtos.UserCreationDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 50)
    private String role; //ROLE_ADMIN, ROLE_USER

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //Lazy para carregar os pedidos apenas quando necessário
    private List<Order> orders;

    @PrePersist //Serve para executar uma ação antes de salvar o objeto no banco de dados
    protected void onCreate(){
        this.deletedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    
    public UserCreationDTO toDTO(){
        return new UserCreationDTO(this.username, this.email, this.password, this.role);
    }

}

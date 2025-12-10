package com.estudosflyway.estudosflyway.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.estudosflyway.estudosflyway.controller.dtos.OrderDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "orders")
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    // id User que fez o pedido, total do pedido, data do pedido, status do pedido

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy para carregar o usuário apenas quando necessário
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, precision = 10, scale = 2) // precision = 10, scale =2 para 10 digitos e 2 casas decimais
    private BigDecimal total;

    @Column(name = "status", nullable = false, length = 50)
    private String status; // PENDING, PAID, CANCELLED

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    public OrderDTO toDTO() {
        return new OrderDTO(this.id, this.user, this.total, this.status, this.createdAt, this.deletedAt);
    }

    @PrePersist // Serve para executar uma ação antes de salvar o objeto no banco de dados
    protected void onCreate() {
        this.deletedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}

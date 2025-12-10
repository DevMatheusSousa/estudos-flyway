package com.estudosflyway.estudosflyway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudosflyway.estudosflyway.model.Order;
import com.estudosflyway.estudosflyway.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user); //buscar ordem de pedido por usuário
    List<Order> findByStatus(String status); //buscar ordem de pedido por status
}

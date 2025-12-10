package com.estudosflyway.estudosflyway.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.estudosflyway.estudosflyway.dtos.OrderDTO;
import com.estudosflyway.estudosflyway.model.Order;
import com.estudosflyway.estudosflyway.model.User;
import com.estudosflyway.estudosflyway.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(@NonNull OrderDTO dto) throws RuntimeException {
        Order order = new Order();

        BeanUtils.copyProperties(dto, order);
        order.setId(dto.id());
        order.setUser(dto.user());
        order.setTotal(dto.total());
        order.setStatus(dto.status());
        order.setCreatedAt(LocalDateTime.now());
        order.setDeletedAt(LocalDateTime.now());

        return orderRepository.save(order);

    }

    public List<OrderDTO> getAllProducts() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return Collections.emptyList();
        }
        return orders.stream().map(Order::toDTO).collect(Collectors.toList()); 
    }

    public void deleteProduct(@NonNull Long id) throws RuntimeException {
        Order order = orderRepository.findById(id).isPresent() ? orderRepository.findById(id).get() : null; 
        if (order == null) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.delete(order);
    }

    List<Order> findByUser(@NonNull User user) throws RuntimeException {
        List<Order> orders = orderRepository.findByUser(user);
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for user: " + user.getId());
        }
        return orders;
    }

    List<Order> findByStatus(@NonNull String status) throws RuntimeException {
        List<Order> orders = orderRepository.findByStatus(status);
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for status: " + status);
        }
        return orders;
    }

    public Order updateProduct(@NonNull Long id, @NonNull OrderDTO dto) throws RuntimeException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        
        order.setUser(dto.user());
        order.setTotal(dto.total());
        order.setStatus(dto.status());
        return orderRepository.save(order);
    }

    public Order updateProductPatch(@NonNull Long id, @NonNull OrderDTO dto) throws RuntimeException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        order.setStatus(dto.status());
        return orderRepository.save(order);
    }
}

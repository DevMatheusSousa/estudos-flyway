package com.estudosflyway.estudosflyway.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudosflyway.estudosflyway.controller.dtos.OrderDTO; 
import com.estudosflyway.estudosflyway.model.Order;
import com.estudosflyway.estudosflyway.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllProducts();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build(); //Retorna 204 No Content se a lista estiver vazia
        }
        return ResponseEntity.ok(orders); //Retorna 200 OK se a lista não estiver vazia
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @NonNull OrderDTO orderDTO) throws RuntimeException {
        Order order = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order.toDTO()); //Retorna 201 Created se o pedido for criado com sucesso
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateProduct(@PathVariable @NonNull Long id, @RequestBody @NonNull OrderDTO orderDTO) throws RuntimeException {
        Order order = orderService.updateProduct(id, orderDTO);
        return ResponseEntity.ok(order.toDTO()); //Retorna 200 OK com o pedido atualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @NonNull Long id) throws RuntimeException {
        orderService.deleteProduct(id);
        return ResponseEntity.noContent().build(); //Retorna 204 No Content se o pedido for deletado com sucesso
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDTO> updateProductPatch(@PathVariable @NonNull Long id, @RequestBody @NonNull OrderDTO orderDTO) throws RuntimeException { 
        Order order = orderService.updateProductPatch(id, orderDTO);
        return ResponseEntity.ok(order.toDTO()); //Retorna 200 OK com o pedido atualizado
    }

    /*end points para testes

    get
    http://localhost:8080/orders/user/{id}

    end point de post para criar uma nova ordem de pedido
    http://localhost:8080/orders
    body:
    {
        "userId": 1,
        "productId": 1,
        "quantity": 1,
        "total": 100.00
        "status": "PENDING"
    }
*/
}

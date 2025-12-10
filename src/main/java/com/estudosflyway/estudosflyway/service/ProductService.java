package com.estudosflyway.estudosflyway.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.estudosflyway.estudosflyway.controller.dtos.ProductDTO; 
import com.estudosflyway.estudosflyway.model.Product; 
import com.estudosflyway.estudosflyway.model.User; 
import com.estudosflyway.estudosflyway.repository.ProductRepository; 

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Serve para injetar o ProductRepository no construtor da classe
public class ProductService {

    // Injeção de Dependência do Repository
    private final ProductRepository productRepository;

    // Relacionamento com user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    // criar produto
    public Product createProduct(@NonNull ProductDTO dto) throws RuntimeException {
        Product product = new Product();

        BeanUtils.copyProperties(dto, product);
        product.setId(dto.id());
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setCategory(dto.category());
        product.setDescription(dto.description());
        product.setStockQuantity(dto.stockQuantity());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    // Atualizar produto
    public Product updateProduct(@NonNull Long id, @NonNull ProductDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setCategory(dto.category());
        product.setDescription(dto.description());
        product.setStockQuantity(dto.stockQuantity());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    // buscar todos os produtos
    public List<Product> getAllProducts() throws RuntimeException {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return Collections.emptyList(); //retorna uma lista vazia se não houver produtos
        }
        return products;
    }

    // buscar por id
    public Product getProductById(@NonNull Long id) throws RuntimeException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Produto não encontrado para o id: " + id);
        }
        return product.get();
    }

    // buscar por categoria
    public List<Product> getProductsByCategory(String category) throws RuntimeException {
        List<Product> products = productRepository.existsByCategory(category);
        if (products.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado para a categoria: " + category);
        }
        return products;
    }

    // bucar por nome
    public List<Product> getProductsByName(String name) throws RuntimeException {
        List<Product> products = productRepository.existsByNameContainingIgnoreCase(name);
        if (products.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado para o nome: " + name);
        }
        return products;
    }
    // deletar produto
    public void deleteProduct(@NonNull Long id) throws RuntimeException {
        Product product = productRepository.findById(id).isPresent() ? productRepository.findById(id).get() : null;
        if (product == null) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        productRepository.delete(product);
    }

    // agora vamo criar migration com nome V1__Create_Product_Table

}

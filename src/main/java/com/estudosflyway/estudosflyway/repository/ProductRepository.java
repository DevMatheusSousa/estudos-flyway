package com.estudosflyway.estudosflyway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudosflyway.estudosflyway.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    //O spring data jpa vai criar a query para buscar por categoria
    List<Product> existsByCategory(String category); //busca por categoria
    List<Product> existsByNameContainingIgnoreCase(String name); //busca por nome 
}

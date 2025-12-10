package com.estudosflyway.estudosflyway.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudosflyway.estudosflyway.dtos.ProductDTO;
import com.estudosflyway.estudosflyway.model.Product;
import com.estudosflyway.estudosflyway.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //uri: http://localhost:8080/products
    @GetMapping
public ResponseEntity<List<ProductDTO>> getAllProducts() {
    List<ProductDTO> products = productService.getAllProducts()
            .stream()
            .map(Product::toDTO)
            .toList();

            //retorna 204 No Content se a lista estiver vazia
           if(products.isEmpty()) {
            return ResponseEntity.noContent().build();
           }
           //retorna 200 OK se a lista não estiver vazia
           return ResponseEntity.ok(products);
}


    //uri: http://localhost:8080/api/products
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @NonNull ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(dto).toDTO());
    }
    //uri: http://localhost:8080/api/products/1
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(productService.getProductById(id).toDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable @NonNull Long id, @RequestBody @NonNull ProductDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto).toDTO());
    }
    //uri: http://localhost:8080/api/products/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @NonNull Long id) {
        
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
    }
}

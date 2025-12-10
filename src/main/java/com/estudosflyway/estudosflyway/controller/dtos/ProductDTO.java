package com.estudosflyway.estudosflyway.controller.dtos;

import java.math.BigDecimal; 

public record ProductDTO(
    Long id,
    String name,
    BigDecimal price,
    String category,
    String description,
    int stockQuantity
) {
    
}

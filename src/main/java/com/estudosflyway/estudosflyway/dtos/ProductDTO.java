package com.estudosflyway.estudosflyway.dtos;

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

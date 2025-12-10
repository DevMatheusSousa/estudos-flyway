package com.estudosflyway.estudosflyway.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.estudosflyway.estudosflyway.model.User;

public record OrderDTO(
    Long id,
    User user,
    BigDecimal total,
    String status,
    LocalDateTime createdAt,
    LocalDateTime deletedAt
) {
    
}

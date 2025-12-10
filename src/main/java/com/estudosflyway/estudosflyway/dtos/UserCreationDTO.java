package com.estudosflyway.estudosflyway.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreationDTO(
    @NotBlank(message = "Nome de usuário é obrigatório")
    String username,
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    String password,
    @NotBlank(message = "Role é obrigatória")
    @Pattern(regexp = "ROLE_ADMIN|ROLE_USER", message = "Role inválida")
    String role //ROLE_ADMIN, ROLE_USER
) {
    
}

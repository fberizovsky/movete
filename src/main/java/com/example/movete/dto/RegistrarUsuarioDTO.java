package com.example.movete.dto;

import java.time.LocalDateTime;

import com.example.movete.model.RoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarUsuarioDTO {
    private Long id;

    @Email(message = "Debe ser un email válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String usuario;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    private RoleEnum role;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDateTime fechaNacimiento;
}

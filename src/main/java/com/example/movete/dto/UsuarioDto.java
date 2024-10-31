package com.example.movete.dto;

import com.example.movete.model.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;

    private String email;

    private String usuario;

    private RoleEnum role;

    private LocalDateTime fechaNacimiento;


}

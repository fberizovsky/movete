package com.example.movete.dto;

import com.example.movete.model.RoleEnum;
import com.example.movete.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;

    private String email;

    private String usuario;

    private String password;

    private RoleEnum role;

    private LocalDate fechaNacimiento;


}

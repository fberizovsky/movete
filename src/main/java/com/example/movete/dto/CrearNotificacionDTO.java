package com.example.movete.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.example.movete.model.TipoNotificacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearNotificacionDTO {

    @NotNull(message = "El título es requerido")
    @NotBlank(message = "El título es requerido")
    private String titulo;
    @NotBlank(message = "El mensaje es requerido")
    private String mensaje;
    @NotBlank(message = "El tipo es requerido")
    private TipoNotificacion tipo;

    @NotBlank(message = "Los usuarios son requeridos")
    private List<Long> usuarios;

    
    
}

package com.example.movete.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarRideDTO {

    @NotBlank(message = "La ubicación de inicio no puede estar vacía")
    private String startLocation;

    @NotBlank(message = "La ubicación de destino no puede estar vacía")
    private String endLocation;

    @NotNull(message = "La hora de inicio no puede estar vacía")
    @Future(message = "La hora de inicio debe ser una fecha futura")
    private LocalDateTime startTime;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    @NotNull(message = "El número máximo de pasajeros no puede estar vacío")
    @Min(value = 1, message = "Debe haber al menos un pasajero")
    private int maxPassengers;

    
    
}

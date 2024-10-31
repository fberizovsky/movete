package com.example.movete.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {
    private long id;
    private String startLocation;
    private String endLocation;
    private LocalDateTime startTime;
    private String description;
    private int maxPassengers;
    private int passengers;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private UsuarioDto usuario;
    private List<BookingDto> bookings;
}

package com.example.movete.dto;

import com.example.movete.model.BookingStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    
    private Long id;

    private UsuarioDto pasajero;

    private RideDto ride;

    private BookingStatusEnum status;

    private LocalDateTime bookingTime;

    private String cancellationReason;

    private Integer passengerRating;

    private Integer driverRating;

    



}

package com.example.movete.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.movete.dto.BookingDto;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Relaciones
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Usuario pasajero;
    
    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;
    
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatusEnum status;
    
    @Column(name = "booking_time")
    private LocalDateTime bookingTime;
    
    @Column(name = "cancellation_reason")
    private String cancellationReason;
    
    @Column(name = "passenger_rating")
    private Integer passengerRating;
    
    @Column(name = "driver_rating")
    private Integer driverRating;


    public Booking(Usuario pasajero, Ride ride, BookingStatusEnum status, LocalDateTime bookingTime, Integer passengerRating, Integer driverRating, String cancellationReason) {
        this.pasajero = pasajero;
        this.ride = ride;
        this.status = status;
        this.bookingTime = bookingTime;
        this.passengerRating = passengerRating;
        this.driverRating = driverRating;
        this.cancellationReason = cancellationReason;
        ride.addBooking(this);
    }

    public BookingDto convertToDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setPasajero(booking.getPasajero().convertToDto(booking.getPasajero()));
        bookingDto.setRide(booking.getRide().convertToDto(booking.getRide()));
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setBookingTime(booking.getBookingTime());
        bookingDto.setCancellationReason(booking.getCancellationReason());
        bookingDto.setPassengerRating(booking.getPassengerRating());
        bookingDto.setDriverRating(booking.getDriverRating());
        return bookingDto;
    }

    public BookingDto convertToDtoReduce(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setPasajero(booking.getPasajero().convertToDto(booking.getPasajero()));
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setBookingTime(booking.getBookingTime());
        return bookingDto;
    }

    public BookingDto convertToDtoMyBookings(Booking booking){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setRide(booking.getRide().convertToDto(booking.getRide()));
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setBookingTime(booking.getBookingTime());
        bookingDto.setRide(booking.getRide().convertToDtoOnlyRide(booking.getRide()));
        return bookingDto;
    }

}
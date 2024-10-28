package com.example.movete.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.model.Booking;
import com.example.movete.model.BookingStatusEnum;
import com.example.movete.repository.BookingRepository;

@Service
public class BookingValidator {
    

    @Autowired 
    private BookingRepository bookingRepository;

    private Booking validateBookingExists(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
    }

    private void validateBookingStatus(Booking booking, BookingStatusEnum status) {
        if (booking.getStatus().equals(status)) {
            throw new IllegalArgumentException("Estado de reserva "+status+" no permitido");
        }
    }

    private void validateBookingUser(Booking booking, Long userId) {
        if (!booking.getPasajero().getId().equals(userId)) {
            throw new IllegalArgumentException("Usuario no autorizado");
        }
    }

    public Booking validateBooking(Long bookingId, Long userId, BookingStatusEnum status) {
        Booking booking = validateBookingExists(bookingId);
        validateBookingStatus(booking, status);
        validateBookingUser(booking, userId);
        return booking;
    }
}

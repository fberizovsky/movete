package com.example.movete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.model.Usuario;
import com.example.movete.repository.BookingRepository;
import com.example.movete.dto.BookingDto;
import java.util.List;
import java.util.ArrayList;

@Service
public class BookingService {



    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingDto> getBookingsByUser(Usuario currentUser) {

        List<BookingDto> bookingsDto= new ArrayList<>();

        bookingRepository.findByPasajero(currentUser).forEach(booking -> {
            bookingsDto.add(booking.convertToDtoMyBookings());
        });

        return bookingsDto;
    }


}

package com.example.movete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movete.model.Booking;
import com.example.movete.model.Ride;
import com.example.movete.model.Usuario;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    

    List<Booking> findByPasajero(Usuario usuario);

    List<Booking> findByRide(Ride ride);
}

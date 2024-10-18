package com.example.movete.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.movete.model.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    
    Optional<Ride> findById(Long rideId);

    List<Ride> findByUsuarioId(Long userId);

    // public List<Ride> findByUsuarioId(Long usuarioId);}

}
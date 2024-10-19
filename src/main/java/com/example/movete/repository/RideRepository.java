package com.example.movete.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.movete.model.Ride;
import com.example.movete.model.Usuario;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    
    Optional<Ride> findById(Long rideId);

    List<Ride> findByUsuario(Usuario usuario);

    // public List<Ride> findByUsuarioId(Long usuarioId);}

}
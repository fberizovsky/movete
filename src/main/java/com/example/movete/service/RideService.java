package com.example.movete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.dto.RegistrarRideDTO;
import com.example.movete.model.Ride;
import com.example.movete.model.Usuario;
import com.example.movete.repository.RideRepository;

@Service
public class RideService {
    
    @Autowired
    private RideRepository rideRepository;

    public void saveRide(Usuario currentUser,RegistrarRideDTO input) {

        // Validar aca si el usuario no puede crear un ride
        
        Ride ride = Ride.convertToEntity(input);
        ride.setUsuario(currentUser); // Establecer el usuario para el viaje
        rideRepository.save(ride); // Guardar el viaje
    }
}

package com.example.movete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.dto.RegistrarRideDTO;
import com.example.movete.dto.RideDto;
import com.example.movete.model.Ride;
import com.example.movete.model.Usuario;
import com.example.movete.repository.RideRepository;
import com.example.movete.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideService {
    
    @Autowired
    private RideRepository rideRepository;

    public void saveRide(Usuario currentUser,RegistrarRideDTO input) {

        if (!currentUser.getIsValidated()) {
            throw new IllegalArgumentException("El usuario no ha sido validado");
        }

        List<Ride> rides = rideRepository.findByUsuario(currentUser);
        for (Ride ride : rides) {
            Boolean boo = Utils.compareHoursDates(ride.getStartTime(), input.getStartTime(), 2);
            if (boo) {
                throw new IllegalArgumentException("Ya tienes un viaje programado a esa hora");
            }
        }
        
        Ride ride = Ride.convertToEntity(input);
        ride.setUsuario(currentUser); // Establecer el usuario para el viaje
        rideRepository.save(ride); // Guardar el viaje
    }

    public List<RideDto> getRides() {
        return rideRepository.findAll().stream().map(Ride::convertToDto).collect(Collectors.toList());
    }

   public List<RideDto> getRidesByUser(Usuario currentUser) {
    return rideRepository.findByUsuario(currentUser).stream()
            .map(Ride::convertToDto)
            .collect(Collectors.toList());
}

}

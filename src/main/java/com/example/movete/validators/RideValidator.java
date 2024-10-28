package com.example.movete.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.dto.RegistrarRideDTO;
import com.example.movete.model.Ride;
import com.example.movete.repository.RideRepository;
import com.example.movete.utils.Utils;

@Service
public class RideValidator {


    @Autowired
    private RideRepository rideRepository;

    private Ride validateRideExists(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));
    }

    private void validateRideUser(Ride ride, Long userId) {
        if (!ride.getUsuario().getId().equals(userId)) {
            throw new IllegalArgumentException("No puedes reservar tu propio viaje");
        }
    }

    private void validateMaxPassengers(Ride ride) {
        if (ride.getPassengers() == ride.getMaxPassengers()) {
            throw new IllegalArgumentException("No hay asientos disponibles, viaje lleno");
        }
    }

    private void validateViajesAlMismoTiempo(List<Ride> rides, RegistrarRideDTO input) {
        for (Ride r : rides) {
            Boolean boo = Utils.compareHoursDates(r.getStartTime(), r.getStartTime(), 2);
            if (boo) {
                throw new IllegalArgumentException("Ya tienes un viaje programado a esa hora");
            }
        }
    }

    public Ride validateReserveRide(Long rideId, Long userId) {
        Ride ride = validateRideExists(rideId);
        validateRideUser(ride, userId);
        validateMaxPassengers(ride);
        return ride;
    }

    public void validateSaveRide(List<Ride> rides, RegistrarRideDTO input) {
        validateViajesAlMismoTiempo(rides, input);
    }
    
}

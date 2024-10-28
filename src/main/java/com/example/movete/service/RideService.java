package com.example.movete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.dto.RegistrarRideDTO;
import com.example.movete.dto.RideDto;
import com.example.movete.model.BookingStatusEnum;
import com.example.movete.model.Ride;
import com.example.movete.model.Usuario;
import com.example.movete.repository.BookingRepository;
import com.example.movete.repository.RideRepository;
import com.example.movete.validators.BookingValidator;
import com.example.movete.validators.RideValidator;
import com.example.movete.model.Booking;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideService {
    
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingValidator bookingValidator;

    @Autowired RideValidator rideValidator;

    public void saveRide(Usuario currentUser,RegistrarRideDTO input) {
        List<Ride> rides = rideRepository.findByUsuario(currentUser);
        
        // Validar que no haya viajes al mismo tiempo
        rideValidator.validateSaveRide(rides, input);
        
        Ride ride = Ride.convertToEntity(input);
        ride.setUsuario(currentUser); // Establecer el usuario para el viaje
        rideRepository.save(ride); // Guardar el viaje
    }

    public List<RideDto> getRides() {
        return rideRepository.findAll().stream().map(ride -> ride.convertToDtoReduce(ride)).collect(Collectors.toList());
    }

   public List<RideDto> getRidesByUser(Usuario currentUser) {
    return rideRepository.findByUsuario(currentUser).stream()
            .map(ride -> ride.convertToDto(ride))
            .collect(Collectors.toList());
    }

    public void reserveRide(Usuario currentUser, Long rideId) {
        Ride ride = rideValidator.validateReserveRide(rideId, currentUser.getId());

        ride.setPassengers(ride.getPassengers() + 1);
        rideRepository.save(ride);

        // Reservar el viaje
        Booking booking = new Booking(
            currentUser,
            ride,
            BookingStatusEnum.PENDING,
            LocalDateTime.now(),
            null,
            null,
            null
        );
        // Guardar la reserva
        bookingRepository.save(booking);
        
        
    }

    public void cancelBooking(Usuario currentUser, Long bookingId) {
        Booking booking = bookingValidator.validateBooking(bookingId, currentUser.getId(), BookingStatusEnum.PENDING);

        booking.setStatus(BookingStatusEnum.CANCELLED);
        booking.setCancellationReason("Cancelado porque no puedo asistir");
        bookingRepository.save(booking);

        Ride ride = booking.getRide();
        ride.setPassengers(ride.getPassengers() - 1);
        rideRepository.save(ride);
    }

}

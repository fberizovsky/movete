package com.example.movete.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.movete.dto.RegistrarRideDTO;
import com.example.movete.dto.RideDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "start_location")
    private String startLocation;

    @Column(nullable = false,name = "end_location")
    private String endLocation;


    @Column(nullable = false, name = "start_time")
    private Date startTime;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "max_passengers")
    private int maxPassengers;

    @Column(nullable = false)
    private int passengers;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date fechaCreacion;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    // Relación con las reservas
    @OneToMany(mappedBy = "ride", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public static Ride convertToEntity(RegistrarRideDTO ride) {
        Ride rideEntity = new Ride();
        rideEntity.setStartLocation(ride.getStartLocation());
        rideEntity.setEndLocation(ride.getEndLocation());
        rideEntity.setStartTime(ride.getStartTime());
        rideEntity.setDescription(ride.getDescription());
        rideEntity.setMaxPassengers(ride.getMaxPassengers());
        return rideEntity;
    }

    public RideDto convertToDto(Ride ride) {
        RideDto rideDto = new RideDto();
        rideDto.setId(ride.getId());
        rideDto.setStartLocation(ride.getStartLocation());
        rideDto.setEndLocation(ride.getEndLocation());
        rideDto.setStartTime(ride.getStartTime());
        rideDto.setDescription(ride.getDescription());
        rideDto.setMaxPassengers(ride.getMaxPassengers());
        rideDto.setPassengers(ride.getPassengers());
        rideDto.setFechaCreacion(ride.getFechaCreacion());
        rideDto.setFechaModificacion(ride.getFechaModificacion());
        rideDto.setUsuario(ride.getUsuario().convertToDto(ride.getUsuario()));
        rideDto.setBookings(ride.getBookings().stream().map(booking -> booking.convertToDtoReduce(booking)).toList());
        return rideDto;
    }

    public RideDto convertToDtoReduce(Ride ride) {
        RideDto rideDto = new RideDto();
        rideDto.setId(ride.getId());
        rideDto.setStartLocation(ride.getStartLocation());
        rideDto.setEndLocation(ride.getEndLocation());
        rideDto.setStartTime(ride.getStartTime());
        rideDto.setDescription(ride.getDescription());
        rideDto.setMaxPassengers(ride.getMaxPassengers());
        rideDto.setPassengers(ride.getPassengers());
        rideDto.setFechaCreacion(ride.getFechaCreacion());
        rideDto.setFechaModificacion(ride.getFechaModificacion());
        rideDto.setUsuario(ride.getUsuario().convertToDto(ride.getUsuario()));
        return rideDto;
    }

    public RideDto convertToDtoOnlyRide(Ride ride){
        RideDto rideDto = new RideDto();
        rideDto.setId(ride.getId());
        rideDto.setStartLocation(ride.getStartLocation());
        rideDto.setEndLocation(ride.getEndLocation());
        rideDto.setStartTime(ride.getStartTime());
        rideDto.setDescription(ride.getDescription());
        rideDto.setMaxPassengers(ride.getMaxPassengers());
        rideDto.setPassengers(ride.getPassengers());
        rideDto.setFechaCreacion(ride.getFechaCreacion());
        rideDto.setFechaModificacion(ride.getFechaModificacion());
        rideDto.setUsuario(ride.getUsuario().convertToDto(ride.getUsuario()));
        return rideDto;
    }
    
    

}

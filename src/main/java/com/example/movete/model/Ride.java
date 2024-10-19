package com.example.movete.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.movete.dto.RegistrarRideDTO;
import com.example.movete.dto.RideDto;
import com.example.movete.dto.UsuarioDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @CreationTimestamp
    @Column(name = "created_at")
    private Date fechaCreacion;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    public static Ride convertToEntity(RegistrarRideDTO ride) {
        Ride rideEntity = new Ride();
        rideEntity.setStartLocation(ride.getStartLocation());
        rideEntity.setEndLocation(ride.getEndLocation());
        rideEntity.setStartTime(ride.getStartTime());
        rideEntity.setDescription(ride.getDescription());
        rideEntity.setMaxPassengers(ride.getMaxPassengers());
        return rideEntity;
    }

    public static RideDto convertToDto(Ride ride) {
        RideDto rideDto = new RideDto();
        rideDto.setId(ride.getId());
        rideDto.setStartLocation(ride.getStartLocation());
        rideDto.setEndLocation(ride.getEndLocation());
        rideDto.setStartTime(ride.getStartTime());
        rideDto.setDescription(ride.getDescription());
        rideDto.setMaxPassengers(ride.getMaxPassengers());
        rideDto.setFechaCreacion(ride.getFechaCreacion());
        rideDto.setFechaModificacion(ride.getFechaModificacion());
        rideDto.setUsuario(ride.getUsuario().convertToDto(ride.getUsuario()));
        return rideDto;
    }
    


}

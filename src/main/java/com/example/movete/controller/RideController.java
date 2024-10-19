package com.example.movete.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movete.dto.RegistrarRideDTO;
import com.example.movete.model.Usuario;
import com.example.movete.service.RideService;

import jakarta.validation.Valid;

@RequestMapping("/rides")
@RestController
public class RideController {
    

    @Autowired
    private RideService rideService;

    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createRide(@Valid @RequestBody RegistrarRideDTO registrarRideDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario currentUser = (Usuario) authentication.getPrincipal();

        rideService.saveRide(currentUser, registrarRideDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-rides")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getMyRides() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario currentUser = (Usuario) authentication.getPrincipal();

        return ResponseEntity.ok(rideService.getRidesByUser(currentUser));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<?> getRides() {
        return ResponseEntity.ok(rideService.getRides());
    }
}

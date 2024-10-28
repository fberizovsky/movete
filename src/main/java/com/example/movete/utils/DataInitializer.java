package com.example.movete.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.movete.model.Booking;
import com.example.movete.model.BookingStatusEnum;
import com.example.movete.model.Ride;
import com.example.movete.model.Role;
import com.example.movete.model.RoleEnum;
import com.example.movete.model.Usuario;
import com.example.movete.repository.BookingRepository;
import com.example.movete.repository.RideRepository;
import com.example.movete.repository.RoleRepository;
import com.example.movete.repository.UsuarioRepository;
import java.util.Date;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private RoleRepository roleRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private BookingRepository bookingRepository;


    @Override
    public void run(String... args) throws Exception {

        Role roleUser = new Role();
        roleUser.setName(RoleEnum.ROLE_USER);
        roleUser.setDescription("Usuario normal");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName(RoleEnum.ROLE_ADMIN);
        roleAdmin.setDescription("Usuario administrador");
        roleRepository.save(roleAdmin);

        Role roleSuperAdmin = new Role();
        roleSuperAdmin.setName(RoleEnum.ROLE_SUPERADMIN);
        roleSuperAdmin.setDescription("Usuario super administrador");
        roleRepository.save(roleSuperAdmin);

        Role roleUserNotVerified = new Role();
        roleUserNotVerified.setName(RoleEnum.ROLE_USER_NOT_VERIFIED);
        roleUserNotVerified.setDescription("Usuario no verificado");
        roleRepository.save(roleUserNotVerified);

    
        Usuario usuario = new Usuario();
        usuario.setUsuario("admin");
        usuario.setEmail("admin@gmail.com");
        usuario.setPassword((passwordEncoder.encode("admin")));
        usuario.setFechaNacimiento(LocalDate.of(2001, 10, 10));
        usuario.setRole(roleAdmin);
        usuarioRepository.save(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setUsuario("user");
        usuario2.setEmail("user@gmail.com");
        usuario2.setPassword((passwordEncoder.encode("user")));
        usuario2.setFechaNacimiento(LocalDate.of(2001, 10, 10));
        usuario2.setRole(roleUser);
        usuarioRepository.save(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setUsuario("superadmin");
        usuario3.setEmail("superadmin@gmail.com");
        usuario3.setPassword(passwordEncoder.encode("superadmin"));
        usuario3.setFechaNacimiento(LocalDate.of(2001, 10, 10));
        usuario3.setRole(roleSuperAdmin);
        usuarioRepository.save(usuario3);

        Usuario usuario4 = new Usuario();
        usuario4.setUsuario("userNotVerified");
        usuario4.setEmail("usernotverified@gmail.com");
        usuario4.setPassword(passwordEncoder.encode("usernotverified"));
        usuario4.setFechaNacimiento(LocalDate.of(2001, 10, 10));
        usuario4.setRole(roleUserNotVerified);
        usuarioRepository.save(usuario4);

        Ride ride = new Ride();
        ride.setUsuario(usuario2);
        ride.setStartLocation("La Plata");
        ride.setEndLocation("Verónica");
        ride.setMaxPassengers(4);
        ride.setDescription("Viaje a Verónica");
        ride.setStartTime(new Date(2025, 9, 10));
        rideRepository.save(ride);

        Ride ride2 = new Ride();
        ride2.setUsuario(usuario2);
        ride2.setStartLocation("La Plata");
        ride2.setEndLocation("Berisso");
        ride2.setMaxPassengers(4);
        ride2.setDescription("Viaje a Berisso");
        ride2.setStartTime(new Date(2025, 9, 10));
        rideRepository.save(ride2);

        Ride ride3 = new Ride();
        ride3.setUsuario(usuario2);
        ride3.setStartLocation("La Plata");
        ride3.setEndLocation("Ensenada");
        ride3.setMaxPassengers(4);
        ride3.setDescription("Viaje a Ensenada");
        ride3.setStartTime(new Date(2025, 9, 10));
        rideRepository.save(ride3);

        Booking booking = new Booking();
        booking.setPasajero(usuario);
        booking.setRide(ride);
        booking.setStatus(BookingStatusEnum.PENDING);
        bookingRepository.save(booking);

        ride.setPassengers(1);
        rideRepository.save(ride);


    }   
}

package com.example.movete.service;

import com.example.movete.dto.IniciarSesionUsuarioDTO;
import com.example.movete.dto.RegistrarUsuarioDTO;
import com.example.movete.model.RoleEnum;
import com.example.movete.model.Usuario;
import com.example.movete.repository.RoleRepository;
import com.example.movete.repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

 

    public Usuario signup(RegistrarUsuarioDTO input) {

        Optional<Usuario> existingUser = userRepository.findByEmail(input.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Usuario user = new Usuario();
        user.setUsuario(input.getUsuario());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setFechaNacimiento(input.getFechaNacimiento());
        user.setIsValidated(false);
        
        //Set ROLE_USER as default
        roleRepository.findByName(RoleEnum.ROLE_USER)
                .ifPresent(user::setRole);
        
        

        return userRepository.save(user);
    }

    public Usuario authenticate(IniciarSesionUsuarioDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}

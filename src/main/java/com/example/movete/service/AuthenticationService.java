package com.example.movete.service;

import com.example.movete.dto.IniciarSesionUsuarioDTO;
import com.example.movete.dto.RegistrarUsuarioDTO;
import com.example.movete.model.RoleEnum;
import com.example.movete.model.Usuario;
import com.example.movete.repository.RoleRepository;
import com.example.movete.repository.UsuarioRepository;
import com.example.movete.validators.AuthenticationValidator;


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

    @Autowired
    private AuthenticationValidator authenticationValidator;

    public Usuario signup(RegistrarUsuarioDTO input) {

        authenticationValidator.validateSignup(input.getEmail());

        Usuario user = new Usuario();
        user.setUsuario(input.getUsuario());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setFechaNacimiento(input.getFechaNacimiento());
        
        //Set ROLE_USER as default
        roleRepository.findByName(RoleEnum.ROLE_USER_NOT_VERIFIED)
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

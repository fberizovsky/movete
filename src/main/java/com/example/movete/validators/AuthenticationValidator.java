package com.example.movete.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.model.Usuario;
import com.example.movete.repository.UsuarioRepository;


@Service
public class AuthenticationValidator {


    @Autowired
    private UsuarioRepository userRepository;

    
    private void validateIfMailExists(String email) {
        Optional<Usuario> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
    }

    public void validateSignup(String email) {
        validateIfMailExists(email);
    }
}


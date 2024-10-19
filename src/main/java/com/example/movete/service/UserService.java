package com.example.movete.service;

import com.example.movete.dto.ResetearPasswordDTO;
import com.example.movete.dto.UsuarioDto;
import com.example.movete.model.Usuario;
import com.example.movete.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDto> allUsers() {

        List<UsuarioDto> usersDto = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            usersDto.add(user.convertToDto(user));
        });

        return usersDto;
    }

    public void resetPassword(Usuario currentUser, ResetearPasswordDTO resetearPasswordDTO) {

        String newPasswordEncoded = passwordEncoder.encode(resetearPasswordDTO.getPassword());

        if (passwordEncoder.matches(resetearPasswordDTO.getPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("La nueva contraseña no puede ser igual a la anterior");
        }

        currentUser.setPassword(newPasswordEncoded);
        userRepository.save(currentUser);
    }
}

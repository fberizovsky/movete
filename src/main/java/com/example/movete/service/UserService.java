package com.example.movete.service;

import com.example.movete.dto.ResetearPasswordDTO;
import com.example.movete.dto.UsuarioDto;
import com.example.movete.model.Role;
import com.example.movete.model.RoleEnum;
import com.example.movete.model.Usuario;
import com.example.movete.repository.RoleRepository;
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
    private RoleRepository roleRepository;

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

    public void updateUserToAdmin(Long usuarioId) {
        
        Usuario user = userRepository.findById(usuarioId).
                orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (user.getRole().getName().equals(RoleEnum.ROLE_ADMIN)) {
            throw new IllegalArgumentException("El usuario ya es administrador");
        }
        if (user.getRole().getName().equals(RoleEnum.ROLE_SUPERADMIN)) {
            throw new IllegalArgumentException("El usuario es superadministrador");
        }

        
        Role role = roleRepository.findByName(RoleEnum.ROLE_ADMIN).get();
        user.setRole(role);

        userRepository.save(user);
    }

    public void downgradeUserToUser(Long usuarioId) {
        
        Usuario user = userRepository.findById(usuarioId).
                orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (user.getRole().getName().equals(RoleEnum.ROLE_USER)) {
            throw new IllegalArgumentException("El usuario ya es usuario");
        }
        if (user.getRole().getName().equals(RoleEnum.ROLE_SUPERADMIN)) {
            throw new IllegalArgumentException("El usuario es superadministrador");
        }

        
        Role role = roleRepository.findByName(RoleEnum.ROLE_USER).get();
        user.setRole(role);

        userRepository.save(user);
    }

    public void validateUser(Long usuarioId) {
        
        Usuario user = userRepository.findById(usuarioId).
                orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (Boolean.TRUE.equals(user.getIsValidated())) {
            throw new IllegalArgumentException("El usuario ya está validado");
        }
                

        user.setIsValidated(true);

        userRepository.save(user);
    }

}

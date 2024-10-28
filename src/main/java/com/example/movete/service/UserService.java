package com.example.movete.service;

import com.example.movete.dto.ResetearPasswordDTO;
import com.example.movete.dto.UsuarioDto;
import com.example.movete.model.Role;
import com.example.movete.model.RoleEnum;
import com.example.movete.model.Usuario;
import com.example.movete.repository.RoleRepository;
import com.example.movete.repository.UsuarioRepository;
import com.example.movete.validators.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
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
    private UserValidator userValidator;

    public List<UsuarioDto> allUsers() {

        List<UsuarioDto> usersDto = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            usersDto.add(user.convertToDto(user));
        });

        return usersDto;
    }

    public void resetPassword(Usuario currentUser, ResetearPasswordDTO resetearPasswordDTO) {
        String password = userValidator.validateResetPassword(resetearPasswordDTO.getPassword(), currentUser.getPassword());


        currentUser.setPassword(password);
        userRepository.save(currentUser);
    }

    public void updateUserToAdmin(Long usuarioId) {
        
        Usuario user = userValidator.validateUpdateUserToAdmin(usuarioId);

        Role role = roleRepository.findByName(RoleEnum.ROLE_ADMIN).get();
        user.setRole(role);

        userRepository.save(user);
    }

    public void downgradeAdminToUser(Long usuarioId) {
        
        Usuario user = userValidator.validateDowngradeAdminToUser(usuarioId);
        Role role = roleRepository.findByName(RoleEnum.ROLE_USER).get();
        user.setRole(role);

        userRepository.save(user);
    }

    public void validateUser(Long usuarioId) {
        
        Usuario user = userValidator.validateValidateUser(usuarioId);


        user.setRole(roleRepository.findByName(RoleEnum.ROLE_USER).get());
        userRepository.save(user);
    }

}

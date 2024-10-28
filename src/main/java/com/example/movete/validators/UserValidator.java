package com.example.movete.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.movete.model.RoleEnum;
import com.example.movete.model.Usuario;
import com.example.movete.repository.UsuarioRepository;

@Service
public class UserValidator {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository userRepository;


    private Usuario validateUserExists(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));   
    }

    private void validateIfUserIsNotUser(Usuario user) {
        if (!user.getRole().getName().equals(RoleEnum.ROLE_USER)) {
            throw new IllegalArgumentException("El usuario no es usuario");
        }
    }

    private void validateIfUserIsValidated(Usuario user) {
        if (!user.getRole().getName().equals(RoleEnum.ROLE_USER_NOT_VERIFIED)) {
            throw new IllegalArgumentException("El usuario ya está validado");
        }
    }


    private void validateIfUserIsNotAdmin(Usuario user) {
        if (!user.getRole().getName().equals(RoleEnum.ROLE_ADMIN)) {
            throw new IllegalArgumentException("El usuario no es administrador");
        }
    }




    private String validatePasswordDifferent(String newPassword, String oldPassword) {

        String newPasswordEncoded = passwordEncoder.encode(newPassword);

        if (passwordEncoder.matches(newPasswordEncoded, oldPassword)) {
            throw new IllegalArgumentException("La nueva contraseña no puede ser igual a la anterior");
        }

        return newPasswordEncoded;
    }
    

    public String validateResetPassword(String newPassword, String oldPassword) {
        return validatePasswordDifferent(newPassword, oldPassword);
    }


    public Usuario validateUpdateUserToAdmin(Long userId) {
        Usuario user = validateUserExists(userId);
        validateIfUserIsNotUser(user);
        return user;
    }

    public Usuario validateDowngradeAdminToUser(Long userId) {
        Usuario user = validateUserExists(userId);
        validateIfUserIsNotAdmin(user);
        return user;
    }

    public Usuario validateValidateUser(Long userId) {
        Usuario user = validateUserExists(userId);
        validateIfUserIsValidated(user);
        return user;
    }
}

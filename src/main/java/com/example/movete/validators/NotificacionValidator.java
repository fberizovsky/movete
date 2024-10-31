package com.example.movete.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.model.Usuario;
import com.example.movete.repository.UsuarioRepository;

@Service
public class NotificacionValidator {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private List<Usuario> validateUsersExists(List<Long> usuariosIds) {
        List<Usuario> usuarios = usuarioRepository.findAllById(usuariosIds);
        if (usuarios.size() != usuariosIds.size()) {
            throw new IllegalArgumentException("Uno o más usuarios no fueron encontrados");
        }
        return usuarios;

    }

    public List<Usuario> validateCrearNotificacion(List<Long> usuariosIds) {
        return validateUsersExists(usuariosIds);
    }
}

package com.example.movete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movete.dto.CrearNotificacionDTO;
import com.example.movete.model.Notificacion;
import com.example.movete.model.NotificacionUsuario;
import com.example.movete.model.Usuario;
import com.example.movete.repository.NotificacionRepository;
import com.example.movete.validators.NotificacionValidator;

import java.util.List;

@Service
public class NotificacionService {
    


    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private NotificacionValidator notificacionValidator;


    public Notificacion crearNotificacion(CrearNotificacionDTO request) {
        // Validar que existan los usuarios
        List<Usuario> usuarios = notificacionValidator.validateCrearNotificacion(request.getUsuarios());

        // Crear la notificación
        Notificacion notificacion = new Notificacion();
        notificacion.setTitulo(request.getTitulo());
        notificacion.setMensaje(request.getMensaje());
        notificacion.setTipo(request.getTipo());

        // Crear las relaciones con los usuarios
        for (Usuario usuario : usuarios) {
            NotificacionUsuario notificacionUsuario = new NotificacionUsuario();
            notificacionUsuario.setUsuario(usuario);
            notificacionUsuario.setNotificacion(notificacion);
            notificacion.addNotificacionUsuario(notificacionUsuario);
        }

        return notificacionRepository.save(notificacion);
    }



}

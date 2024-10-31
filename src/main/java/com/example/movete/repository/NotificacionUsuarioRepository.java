package com.example.movete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.movete.model.NotificacionUsuario;
import com.example.movete.model.Usuario;

@Repository
public interface NotificacionUsuarioRepository extends JpaRepository<NotificacionUsuario, Long> {

    // Buscar notificaciones de un usuario
    List<NotificacionUsuario> findByUsuarioOrderByNotificacionFechaCreacionDesc(Usuario usuarioId);

    
    // Contar notificaciones no leídas
    long countByUsuarioAndLeida(Usuario usuarioId, boolean leida);
}
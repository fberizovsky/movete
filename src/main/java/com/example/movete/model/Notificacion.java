package com.example.movete.model;

import org.hibernate.annotations.CreationTimestamp;

import com.example.movete.dto.NotificacionDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notificaciones")
public class Notificacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensaje;

    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoNotificacion tipo;


    @OneToMany(mappedBy = "notificacion", fetch = FetchType.LAZY,  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificacionUsuario> notificacionUsuarios = new ArrayList<>();


    public void addNotificacionUsuario(NotificacionUsuario notificacionUsuario) {
        this.notificacionUsuarios.add(notificacionUsuario);
    }

    public NotificacionDto convertToDto(NotificacionUsuario notificacionUsuario) {
        NotificacionDto notificacionDto = new NotificacionDto();
        notificacionDto.setId(this.id);
        notificacionDto.setTitulo(this.titulo);
        notificacionDto.setMensaje(this.mensaje);
        notificacionDto.setFechaCreacion(this.fechaCreacion);
        notificacionDto.setLeida(notificacionUsuario.isLeida());
        return notificacionDto;
    }

}
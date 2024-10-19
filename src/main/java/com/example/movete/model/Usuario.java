package com.example.movete.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.movete.dto.UsuarioDto;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;


    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName().toString());
        return List.of(authority);
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    

    public UsuarioDto convertToDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setUsuario(usuario.getUsuario());
        usuarioDto.setRole(usuario.getRole().getName());
        usuarioDto.setFechaNacimiento(usuario.getFechaNacimiento());
        return usuarioDto;
    }
    
}

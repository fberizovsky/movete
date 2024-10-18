
package com.example.movete.repository;
import com.example.movete.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuarioAndPassword(String usuario, String password);

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findByEmail(String email);
}
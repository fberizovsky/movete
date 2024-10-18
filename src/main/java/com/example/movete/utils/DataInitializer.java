package com.example.movete.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.movete.model.Role;
import com.example.movete.model.RoleEnum;
import com.example.movete.model.Usuario;
import com.example.movete.repository.RoleRepository;
import com.example.movete.repository.UsuarioRepository;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        Role roleUser = new Role();
        roleUser.setName(RoleEnum.ROLE_USER);
        roleUser.setDescription("Usuario normal");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName(RoleEnum.ROLE_ADMIN);
        roleAdmin.setDescription("Usuario administrador");
        roleRepository.save(roleAdmin);

        Role roleSuperAdmin = new Role();
        roleSuperAdmin.setName(RoleEnum.ROLE_SUPERADMIN);
        roleSuperAdmin.setDescription("Usuario super administrador");
        roleRepository.save(roleSuperAdmin);

    
        Usuario usuario = new Usuario();
        usuario.setUsuario("admin");
        usuario.setEmail("admin@gmail.com");
        usuario.setPassword((passwordEncoder.encode("admin")));
        usuario.setFechaNacimiento(LocalDate.of(2001, 10, 10));
        usuario.setRole(roleAdmin);
        usuarioRepository.save(usuario);

        Usuario usuario2 = new Usuario();
        usuario2.setUsuario("user");
        usuario2.setEmail("user@gmail.com");
        usuario2.setPassword((passwordEncoder.encode("user")));
        usuario2.setFechaNacimiento(LocalDate.of(2001, 10, 10));
        usuario2.setRole(roleUser);
        usuarioRepository.save(usuario2);

        Usuario usuario3 = new Usuario();
        usuario3.setUsuario("superadmin");
        usuario3.setEmail("superadmin@gmail.com");
        usuario3.setPassword(passwordEncoder.encode("superadmin"));
        usuario3.setFechaNacimiento(LocalDate.of(2001, 10, 10));
        usuario3.setRole(roleSuperAdmin);
        usuarioRepository.save(usuario3);

    }   
}

package com.example.movete.controller;

import com.example.movete.dto.ResetearPasswordDTO;
import com.example.movete.dto.UsuarioDto;
import com.example.movete.model.Usuario;
import com.example.movete.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario currentUser = (Usuario) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser.convertToDto(currentUser));
    }

    
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
    public ResponseEntity<List<UsuarioDto>> allUsers() {
        List <UsuarioDto> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/reset-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetearPasswordDTO resetearPasswordDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario currentUser = (Usuario) authentication.getPrincipal();

        userService.resetPassword(currentUser, resetearPasswordDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-to-admin/{usuarioId}")
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public ResponseEntity<?> updateUserToAdmin(@PathVariable Long usuarioId) {
        userService.updateUserToAdmin(usuarioId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/downgrade-to-user/{usuarioId}")
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public ResponseEntity<?> downgradeAdminToUser(@PathVariable Long usuarioId) {
        userService.downgradeAdminToUser(usuarioId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/validate/{usuarioId}")
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public ResponseEntity<?> validateUser(@PathVariable Long usuarioId) {
        userService.validateUser(usuarioId);

        return ResponseEntity.ok().build();
    }
}
package com.pixels.parquediversiones.web.controller;

import com.pixels.parquediversiones.domain.dto.AuthenticationRequest;
import com.pixels.parquediversiones.domain.dto.AuthenticationResponse;
import com.pixels.parquediversiones.persistence.UsuarioRepository;
import com.pixels.parquediversiones.persistence.entity.Usuario;
import com.pixels.parquediversiones.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UsuarioRepository usuarioRepository;

    
}

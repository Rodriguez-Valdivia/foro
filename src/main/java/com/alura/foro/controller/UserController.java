package com.alura.foro.controller;

import com.alura.foro.domain.DatosRegistroUsuario;
import com.alura.foro.infra.repository.UserRepository;
import com.alura.foro.infra.security.DatoJWT;
import com.alura.foro.infra.security.TokenService;
import com.alura.foro.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/registrar")
    public String registro(@Valid @RequestBody DatosRegistroUsuario datosRegistroUsuario){
        var usuario = new Usuario(datosRegistroUsuario.email(), passwordEncoder.encode(datosRegistroUsuario.password()));
        userRepository.save(usuario);
        return "Registro exitoso";
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody DatosRegistroUsuario datosRegistroUsuario){
        Authentication token = new UsernamePasswordAuthenticationToken(datosRegistroUsuario.email(), datosRegistroUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(token);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatoJWT(JWTtoken));
    }
}

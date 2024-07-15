package com.alura.foro.infra.repository;

import com.alura.foro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
}

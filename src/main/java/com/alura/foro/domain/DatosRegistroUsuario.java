package com.alura.foro.domain;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank(message = "Email es obligatorio")
        String email,
        @NotBlank(message = "Passowrd es obligatorio")
        String password
) {
}

package com.alura.foro.domain;

import jakarta.validation.constraints.NotBlank;

public record DatosCrearTopico(
        @NotBlank(message = "El titulo es obligatorio")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje
) {
}

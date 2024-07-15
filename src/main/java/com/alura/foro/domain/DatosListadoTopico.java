package com.alura.foro.domain;

import java.util.Date;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        Date fechaCreacion
) {
}

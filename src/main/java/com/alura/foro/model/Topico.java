package com.alura.foro.model;

import com.alura.foro.domain.DatosCrearTopico;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private Date fechaCreacion;

    @ManyToOne
    private Usuario usuario;

    public Topico(DatosCrearTopico datosCrearTopico, Usuario usuario){
        this.titulo = datosCrearTopico.titulo();
        this.mensaje = datosCrearTopico.mensaje();
        this.usuario = usuario;
        this.fechaCreacion = new Date();
    }

}

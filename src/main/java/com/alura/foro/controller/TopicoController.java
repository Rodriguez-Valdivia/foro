package com.alura.foro.controller;

import com.alura.foro.domain.DatosCrearTopico;
import com.alura.foro.domain.DatosListadoTopico;
import com.alura.foro.infra.repository.TopicoRepository;
import com.alura.foro.model.Topico;
import com.alura.foro.model.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity crearTopico(@Valid @RequestBody DatosCrearTopico datosCrearTopico){
        var optionalTopico = topicoRepository.findByTituloAndMensaje(datosCrearTopico.titulo(), datosCrearTopico.mensaje());
        if(optionalTopico.isPresent()) {
            return ResponseEntity.badRequest().body("El tópico ya existe");
        }
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var topico = new Topico(datosCrearTopico, usuario);
        topicoRepository.save(topico);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<DatosListadoTopico> obtenerTopicos(Pageable paginacion){
        var topicos = topicoRepository.findAll(paginacion).map(topico -> new DatosListadoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion()
        ));
        return topicos;
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerTopicoPorId(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);

        if(topicoOptional.isPresent()){
            var topico = topicoOptional.get();
            return ResponseEntity.ok(new DatosListadoTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaCreacion()
            ));
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody DatosCrearTopico datosCrearTopico){
        var optionalTopico = topicoRepository.findById(id);
        if(optionalTopico.isPresent()) {
            var topico = optionalTopico.get();
            topico.setMensaje(datosCrearTopico.mensaje());
            topico.setTitulo(datosCrearTopico.titulo());
            topicoRepository.save(topico);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        if(topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

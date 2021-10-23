package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@FunctionalInterface
public interface RecomendarPorTipoYCategoria {
    Flux<RecursoDTO> get(@Valid String tipoRecurso, @Valid String categoriaRecurso);
}

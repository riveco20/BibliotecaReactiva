package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
@FunctionalInterface
public interface GuardarRecursoI {
    Mono<RecursoDTO> apply(@Valid RecursoDTO recursoDTO);
}

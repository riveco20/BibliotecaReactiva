package com.biblioteca.bibliotecaSpringBoot.useCases;


import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class PrestarRecursoUseCase implements Function<String, Mono<String>> {

    private final RepositorieEbook repositorieEbook;
    private final ActualizarRecursoUseCase actualizarRecursoUseCase;
    private final MapperUtils mapperUtils;

    public PrestarRecursoUseCase(RepositorieEbook repositorieEbook, ActualizarRecursoUseCase actualizarRecursoUseCase, MapperUtils mapperUtils) {
        this.repositorieEbook = repositorieEbook;
        this.actualizarRecursoUseCase = actualizarRecursoUseCase;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id,"El id no puede ser nulo");
        return repositorieEbook.findById(id).flatMap(
                recurso -> {
                    if (recurso.getCantidadDeEbookDisponible() > recurso.getCantidadDeEbookPrestada()){
                        recurso.setFechaPrestadoEbook(LocalDate.now());
                        recurso.setCantidadDeEbookPrestada(recurso.getCantidadDeEbookPrestada() + 1);
                        return actualizarRecursoUseCase.apply(mapperUtils.mapEntityToResource().apply(recurso)).thenReturn("El recurso " + recurso.getNombreEbook() + " no esta disponible");
                    }
                    return Mono.just("El recurso " + recurso.getNombreEbook() + " no cuenta con unidades disponibles");
                }
        );
    }
}

package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class RegresarRecursoUseCase implements Function<String, Mono<String>> {
    private final RepositorieEbook repositorieEbook;
    private final MapperUtils mapperUtils;
    private final ActualizarRecursoUseCase actualizarRecursoUseCase;

    public RegresarRecursoUseCase(RepositorieEbook repositorieEbook, MapperUtils mapperUtils, ActualizarRecursoUseCase actualizarRecursoUseCase) {
        this.repositorieEbook = repositorieEbook;
        this.mapperUtils = mapperUtils;
        this.actualizarRecursoUseCase = actualizarRecursoUseCase;
    }


    @Override
    public Mono<String> apply(String id) {

        Objects.requireNonNull(id, "El id no puede ser nulo");
        return repositorieEbook.findById(id).flatMap(
                recurso -> {
                    if (recurso.getCantidadDeEbookPrestada() > 0) {
                        recurso.setCantidadDeEbookPrestada(recurso.getCantidadDeEbookPrestada() - 1);
                        return actualizarRecursoUseCase.apply(mapperUtils.mapEntityToResource().apply(recurso)).thenReturn("El recurso " + recurso.getNombreEbook() + " fue devuelto");
                    }
                    return Mono.just("El recurso " + recurso.getNombreEbook() + " no ha sido prestado");
                }
        );

    }
}

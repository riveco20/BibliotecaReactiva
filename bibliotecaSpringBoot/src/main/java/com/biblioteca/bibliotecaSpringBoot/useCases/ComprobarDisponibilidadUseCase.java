package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class ComprobarDisponibilidadUseCase implements Function<String, Mono<String>> {

    private final RepositorieEbook repositorieEbook;

    public ComprobarDisponibilidadUseCase(RepositorieEbook repositorieEbook) {
        this.repositorieEbook = repositorieEbook;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id,"El id no puede ser nulo");
        return repositorieEbook.findById(id)
                .map(recurso ->
                        recurso.getCantidadDeEbookDisponible() > recurso.getCantidadDeEbookPrestada()
                                ? String.valueOf("El recurso " + recurso.getNombreEbook() + " cuenta con " + (recurso.getCantidadDeEbookDisponible() - recurso.getCantidadDeEbookPrestada()) + " unidades disponibles")
                                : String.valueOf("El recurso " + recurso.getNombreEbook() + "no se encuentra disponible")
                );
    }
}

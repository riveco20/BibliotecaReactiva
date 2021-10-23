package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
public class EliminarRecursoUseCAse implements Function<String, Mono<Void>> {
    private final RepositorieEbook repositorieEbook;

    public EliminarRecursoUseCAse(RepositorieEbook repositorieEbook) {
        this.repositorieEbook = repositorieEbook;
    }


    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id,"Se requiere el Id");
        return repositorieEbook.deleteById(id);
    }
}

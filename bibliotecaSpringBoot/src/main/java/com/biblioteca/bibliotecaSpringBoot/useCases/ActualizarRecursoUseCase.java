package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ActualizarRecursoUseCase implements GuardarRecursoI {
    private final RepositorieEbook repositorieEbook;
    private final MapperUtils mapperUtils;

    public ActualizarRecursoUseCase(RepositorieEbook repositorieEbook, MapperUtils mapperUtils) {
        this.repositorieEbook = repositorieEbook;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "Se requiere una id valida");
        return repositorieEbook.save(mapperUtils.mapperToRecurso().apply(recursoDTO)).map(recurso -> mapperUtils.mapEntityToResource().apply(recurso));

    }
}

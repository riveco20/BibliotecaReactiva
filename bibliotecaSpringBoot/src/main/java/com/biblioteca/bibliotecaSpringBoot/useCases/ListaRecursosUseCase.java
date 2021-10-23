package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListaRecursosUseCase implements Supplier<Flux<RecursoDTO>> {
   private final RepositorieEbook repositorieEbook;
   private final MapperUtils mapperUtils;

    public ListaRecursosUseCase(RepositorieEbook repositorieEbook, MapperUtils mapperUtils) {
        this.repositorieEbook = repositorieEbook;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return repositorieEbook.findAll()
                .map(mapperUtils.mapEntityToResource());
    }
}

package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
@Validated
public class RecomendarPorTipoYCategoriaUseCase implements RecomendarPorTipoYCategoria{
    private final RepositorieEbook repositorieEbook;
    private final MapperUtils mapperUtils;

    public RecomendarPorTipoYCategoriaUseCase(RepositorieEbook repositorieEbook, MapperUtils mapperUtils) {
        this.repositorieEbook = repositorieEbook;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Flux<RecursoDTO> get(String tipoRecurso, String categoriaRecurso) {
        Objects.requireNonNull(tipoRecurso, "debe contener al menos un tipo");
        Objects.requireNonNull(categoriaRecurso, "debe contener al menos una categoria");
        return repositorieEbook.findBytipoandCategoria(tipoRecurso, categoriaRecurso).map(mapperUtils.mapEntityToResource()).distinct();
    }

}

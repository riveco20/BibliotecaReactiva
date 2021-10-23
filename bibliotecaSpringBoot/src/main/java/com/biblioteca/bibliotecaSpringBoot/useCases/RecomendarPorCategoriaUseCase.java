package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import com.biblioteca.bibliotecaSpringBoot.repositories.RepositorieEbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class RecomendarPorCategoriaUseCase implements RecomendarPorCategoria{

    private final RepositorieEbook repositorieEbook;
    private final MapperUtils mapperUtils;

    public RecomendarPorCategoriaUseCase(RepositorieEbook repositorieEbook, MapperUtils mapperUtils) {
        this.repositorieEbook = repositorieEbook;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Flux<RecursoDTO> get(String categoriaRecurso) {
        return repositorieEbook.findByCategoriaRecurso(categoriaRecurso).map(mapperUtils.mapEntityToResource());
    }
}

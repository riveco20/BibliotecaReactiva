package com.biblioteca.bibliotecaSpringBoot.repositories;


import com.biblioteca.bibliotecaSpringBoot.collections.Recurso;;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface RepositorieEbook extends ReactiveMongoRepository<Recurso, String> {
    Flux<Recurso> findBytipoRecurso(String tipoRecurso);
    Flux<Recurso> findByCategoriaRecurso( String categoria);
    Flux<Recurso> findBytipoandCategoria(String tipoRecurso,String categoria);

}

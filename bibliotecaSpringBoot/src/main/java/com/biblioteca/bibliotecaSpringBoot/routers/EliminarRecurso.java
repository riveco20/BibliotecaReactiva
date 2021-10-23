package com.biblioteca.bibliotecaSpringBoot.routers;

import com.biblioteca.bibliotecaSpringBoot.useCases.EliminarRecursoUseCAse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EliminarRecurso {
    @Bean
    public RouterFunction<ServerResponse> delete(EliminarRecursoUseCAse eliminarRecursoUseCAse) {
        return route(
                DELETE("/recursos/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(eliminarRecursoUseCAse.apply(request.pathVariable("id")), Void.class))
        );
    }
}

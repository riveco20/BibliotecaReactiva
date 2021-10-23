package com.biblioteca.bibliotecaSpringBoot.routers;

import com.biblioteca.bibliotecaSpringBoot.useCases.RegresarRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class RegresarRecurso {
    @Bean
    public RouterFunction<ServerResponse> returnResource(RegresarRecursoUseCase regresarRecursoUseCase) {
        return route(
                PUT("/recursos/devolver/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(regresarRecursoUseCase.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }
}

package com.biblioteca.bibliotecaSpringBoot.routers;

import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import com.biblioteca.bibliotecaSpringBoot.useCases.GuardarRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GuardarRecursos {

    @Bean
    public RouterFunction<ServerResponse> guardar(GuardarRecursoUseCase guardarRecursoUseCase){
        Function<RecursoDTO, Mono<ServerResponse>> executor = resourceDTO -> guardarRecursoUseCase.apply(resourceDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/recursos/agregar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class).flatMap(executor)
        );

    };

}

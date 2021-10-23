package com.biblioteca.bibliotecaSpringBoot.routers;

import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import com.biblioteca.bibliotecaSpringBoot.useCases.RecomendarPorTipoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
public class RecomendarRecursoTipo {
    @Bean
    public RouterFunction<ServerResponse> recommendByType(RecomendarPorTipoUseCase recomendarPorTipoUseCase) {
        return route(
                GET("/recursos/recomendarxtipo/{tipo}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recomendarPorTipoUseCase.get(request.pathVariable("tipo")), RecursoDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
}

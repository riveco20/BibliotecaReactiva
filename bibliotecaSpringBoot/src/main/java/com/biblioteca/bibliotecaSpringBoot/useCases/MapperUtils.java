package com.biblioteca.bibliotecaSpringBoot.useCases;

import com.biblioteca.bibliotecaSpringBoot.collections.Recurso;
import com.biblioteca.bibliotecaSpringBoot.models.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<RecursoDTO, Recurso> mapperToRecurso(){
        return actualizarRecurso ->{
            var recurso = new Recurso();
            recurso.setId(actualizarRecurso.getId());
            recurso.setNombreEbook(actualizarRecurso.getNombreEbook());
            recurso.setCategoriaDeLibro(actualizarRecurso.getCategoriaDeLibro());
            recurso.setTipoDeEbook(actualizarRecurso.getTipoDeEbook());
            recurso.setFechaPrestadoEbook(actualizarRecurso.getFechaPrestadoEbook());
            recurso.setCantidadDeEbookDisponible(actualizarRecurso.getCantidadDeEbookDisponible());
            recurso.setCantidadDeEbookPrestada(actualizarRecurso.getCantidadDeEbookPrestada());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapEntityToResource(){
        return entity -> new RecursoDTO(
                entity.getId(),
                entity.getNombreEbook(),
                entity.getCategoriaDeLibro(),
                entity.getTipoDeEbook(),
                entity.getFechaPrestadoEbook(),
                entity.getCantidadDeEbookDisponible(),
                entity.getCantidadDeEbookPrestada()
        );
    }

}

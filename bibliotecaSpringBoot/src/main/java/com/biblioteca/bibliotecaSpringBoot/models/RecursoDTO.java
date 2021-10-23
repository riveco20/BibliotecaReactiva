package com.biblioteca.bibliotecaSpringBoot.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
@Data
public class RecursoDTO {


    private String id;
    @NotBlank(message ="El Recurso debe contener un nombre ")
    private String nombreEbook;
    private String categoriaDeLibro;
    @NotBlank(message = "Debe contener por lo menos un tipo")
    private String tipoDeEbook;
    @NotBlank(message = "La tematica no puede estar vacia")
    private LocalDate fechaPrestadoEbook;
    @NotNull(message = "La cantidad siponible no deb estar vacia")
    private Integer cantidadDeEbookDisponible;
    private Integer cantidadDeEbookPrestada;


}

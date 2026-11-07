package com.example.practica13.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // GENERA GETTERS Y SETTERS
@NoArgsConstructor // la semántica lo dice xd
@AllArgsConstructor
public class Recurso {

    private Long id;

    private String titulo;

    private String url;

    private String tipo;

    private String Categoria;


}

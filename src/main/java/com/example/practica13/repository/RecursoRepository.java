package com.example.practica13.repository;

import com.example.practica13.model.Recurso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RecursoRepository {
    // Simulación de la tabla en base de datos en memoria
    private final List<Recurso> recursosDb = new ArrayList<>();
    private Long idCounter = 1L;

    // Obtener todos los recursos
    public List<Recurso> findAll() {
        return recursosDb;
    }

    // Buscar por ID usando Optional para evitar NullPointerException
    public Optional<Recurso> findById(Long id) {
        return recursosDb.stream()
                .filter(recurso -> recurso.getId().equals(id))
                .findFirst();
    }

    // Guardar (Crear o Actualizar)
    public Recurso save(Recurso recurso) {
        if (recurso.getId() == null) {
            // Es la creación de un nuevo recurso: asignamos un ID autoincremental
            recurso.setId(idCounter++);
            recursosDb.add(recurso);
            return recurso;
        } else {
            // Es una actualización: reemplazamos el registro existente
            for (int i = 0; i < recursosDb.size(); i++) {
                if (recursosDb.get(i).getId().equals(recurso.getId())) {
                    recursosDb.set(i, recurso);
                    return recurso;
                }
            }
            return recurso;
        }
    }

    // Eliminar por ID
    public boolean deleteById(Long id) {
        return recursosDb.removeIf(recurso -> recurso.getId().equals(id));
    }
}

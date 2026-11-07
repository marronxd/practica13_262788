package com.example.practica13.service;

import com.example.practica13.model.Recurso;
import com.example.practica13.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> obtenerTodos() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> obtenerPorId(Long id) {
        return recursoRepository.findById(id);
    }
    // Reemplaza todo
    public Optional<Recurso> actualizarCompleto(Long id, Recurso detalles) {
        return recursoRepository.findById(id).map(existente -> {
            existente.setTitulo(detalles.getTitulo());
            existente.setUrl(detalles.getUrl());
            existente.setTipo(detalles.getTipo());
            existente.setCategoria(detalles.getCategoria());
            return recursoRepository.save(existente);
        });
    }

    // Se acrtualizan partes especificas
    public Optional<Recurso> actualizarParcial(Long id, Recurso detalles) {
        return recursoRepository.findById(id).map(existente -> {
            if (detalles.getTitulo() != null) existente.setTitulo(detalles.getTitulo());
            if (detalles.getUrl() != null) existente.setUrl(detalles.getUrl());
            if (detalles.getTipo() != null) existente.setTipo(detalles.getTipo());
            if (detalles.getCategoria() != null) existente.setCategoria(detalles.getCategoria());
            return recursoRepository.save(existente);
        });
    }
    public Recurso guardar(Recurso recurso) {

        return recursoRepository.save(recurso);
    }

    public boolean eliminar(Long id) {
        return recursoRepository.deleteById(id);
    }
}

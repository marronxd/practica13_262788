package com.example.practica13.controller;

import com.example.practica13.model.Recurso;
import com.example.practica13.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * ResponseEntity es una respuesta http con formato json
 *
 * optional maneja los null pointer, obliga a buscar primero en la bd
 */
@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoservice;

    @GetMapping
    public List<Recurso> listar(){
        return recursoservice.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> obtenerPorId(@PathVariable Long id){
        Optional<Recurso> buscado = recursoservice.obtenerPorId(id);
        if( buscado.isPresent()){
            Recurso r = buscado.get();
            return ResponseEntity.ok(r); // REGRESA ESTATUS 200 DE OK Y EL OBJETO EN FORMA JSON
        }else{
            return ResponseEntity.notFound().build(); // REGRESA NO ENCONTRADO 404 Y CUERPO VACÍO
        }
    }

    /**
     * Regresa una respuesta http con el estatus de creado
     * @param recurso
     * @return
     */
    @PostMapping
    public ResponseEntity<Recurso> crear(@RequestBody Recurso recurso){
        Recurso nuevoRecurso = recursoservice.guardar(recurso);
        return new ResponseEntity<>(nuevoRecurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> actualizarTodo(@PathVariable Long id,
                                              @RequestBody Recurso detalles){
        Optional<Recurso> actualizado = recursoservice.actualizarCompleto(id, detalles);
        if(actualizado.isPresent()){
            Recurso r = actualizado.get();
            return ResponseEntity.ok(r);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Recurso> actualizarAlgunasCosas(@PathVariable Long id,
                                              @RequestBody Recurso detalles){
        Optional<Recurso> actualizado = recursoservice.actualizarParcial(id, detalles);
        if(actualizado.isPresent()){
            Recurso r = actualizado.get();
            return ResponseEntity.ok(r);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
// el void es porque pues nbo tiene nada el cuerpo porque se eliminó
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecurso(@PathVariable Long id){
        if (recursoservice.eliminar(id)) {
            return ResponseEntity.noContent().build(); // DICE QUE NO HAY CONTENIDO, O SEA SE ELIMINA
        } else {
            return ResponseEntity.notFound().build(); // NO EXISTE
        }
    }
}

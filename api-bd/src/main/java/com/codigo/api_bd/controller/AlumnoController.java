package com.codigo.api_bd.controller;

import com.codigo.api_bd.dto.AlumnoCreateRequest;
import com.codigo.api_bd.dto.AlumnoResponse;
import com.codigo.api_bd.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public AlumnoResponse crear(@Valid @RequestBody AlumnoCreateRequest request){
        return alumnoService.crear(request);
    }
    // CONSULTAR por id
    @GetMapping("/{id}")
    public AlumnoResponse consultar(@PathVariable UUID id){
        return alumnoService.consultar(id);
    }

    // LISTAR todos
    @GetMapping
    public List<AlumnoResponse> listar(){
        return alumnoService.listar();
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public AlumnoResponse actualizar(@PathVariable UUID id,
                                     @Valid @RequestBody AlumnoCreateRequest request){
        return alumnoService.actualizar(id, request);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable UUID id){
        alumnoService.eliminar(id);
    }



}

package com.codigo.api_bd.service;

import com.codigo.api_bd.dto.AlumnoCreateRequest;
import com.codigo.api_bd.dto.AlumnoResponse;
import com.codigo.api_bd.model.Alumno;
import com.codigo.api_bd.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Service
public class AlumnoService {
    private final AlumnoRepository repository;

    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AlumnoResponse crear(AlumnoCreateRequest request){
        Alumno a = new Alumno(request.nombre(), request.edad());
        Alumno saved = repository.save(a);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public AlumnoResponse consultar(UUID id){
        Alumno a = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
        return toResponse(a);
    }

    @Transactional(readOnly = true)
    public List<AlumnoResponse> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public AlumnoResponse actualizar(UUID id, AlumnoCreateRequest request){
        Alumno a = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));

        a.setNombre(request.nombre());
        a.setEdad(request.edad());

        Alumno saved = repository.save(a);
        return toResponse(saved);
    }

    @Transactional
    public void eliminar(UUID id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado");
        }
        repository.deleteById(id);
    }

    private AlumnoResponse toResponse(Alumno a){
        return new AlumnoResponse(a.getId(), a.getNombre(), a.getEdad());
    }
}
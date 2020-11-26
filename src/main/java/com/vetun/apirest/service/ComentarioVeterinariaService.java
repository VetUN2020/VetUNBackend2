package com.vetun.apirest.service;

import com.vetun.apirest.model.ComentarioVeterinaria;
import com.vetun.apirest.repository.ComentarioVeterinariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioVeterinariaService {
    private ComentarioVeterinariaRepository comentarioVeterinariaRepository;

    public ComentarioVeterinariaService(ComentarioVeterinariaRepository comentarioVeterinariaRepository){this.comentarioVeterinariaRepository = comentarioVeterinariaRepository;}

    public List<ComentarioVeterinaria> findComentariosVeterinaria(Integer idveterinaria){return comentarioVeterinariaRepository.findByIdVeterinariaIdVeterinaria(idveterinaria);}

    public void save( ComentarioVeterinaria comentarioVeterinaria ){
        comentarioVeterinariaRepository.save( comentarioVeterinaria );
    }
}

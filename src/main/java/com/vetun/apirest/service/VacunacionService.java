package com.vetun.apirest.service;

import com.vetun.apirest.model.Vacunacion;
import com.vetun.apirest.repository.VacunacionRepository;
import org.springframework.stereotype.Service;

@Service
public class VacunacionService {

    private VacunacionRepository vacunacionRepository;

    public VacunacionService( VacunacionRepository vacunacionRepository){
        this.vacunacionRepository = vacunacionRepository;
    }

    public Vacunacion findById( Integer id ){
        return vacunacionRepository.findById( id ).orElse(null);
    }

    public void save ( Vacunacion vacunacion ){
        vacunacionRepository.save( vacunacion);
    }
}

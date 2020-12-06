package com.vetun.apirest.service;

import com.vetun.apirest.model.Costo;
import com.vetun.apirest.model.Medico;
import com.vetun.apirest.repository.CostoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostoService {

    private CostoRepository costoRepository;

    public CostoService(CostoRepository costoRepository) {
        this.costoRepository = costoRepository;
    }

    public Costo findById(Integer id ){
        return  costoRepository.findById( id ).orElse( null );
    }

    public void save(Costo costo){
        costoRepository.save(costo);
    }

    public List<Costo> findByIdMedico(Medico idMedico){ return costoRepository.findByIdMedico(idMedico); }

}

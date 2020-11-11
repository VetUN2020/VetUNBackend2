package com.vetun.apirest.service;

import com.vetun.apirest.model.Cita;
import com.vetun.apirest.model.Costo;
import com.vetun.apirest.repository.CostoRepository;
import org.springframework.stereotype.Service;

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

}

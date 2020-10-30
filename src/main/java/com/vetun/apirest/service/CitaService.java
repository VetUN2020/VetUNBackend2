package com.vetun.apirest.service;

import com.vetun.apirest.model.Cita;
import com.vetun.apirest.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CitaService {

    private CitaRepository citaRepository;

    public CitaService( CitaRepository citaRepository){
        this.citaRepository = citaRepository;
    }

    public Cita findById( Integer id ){
        return  citaRepository.findById( id ).orElse( null );
    }

    public void save( Cita cita ){
        citaRepository.save( cita );
    }

    /*public List<Cita> findCitas(Timestamp a, Timestamp b){
        return  citaRepository.findByFechaCitaBetween(a, b);
    }*/
}

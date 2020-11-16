package com.vetun.apirest.service;

import com.vetun.apirest.model.Cita;
import com.vetun.apirest.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
//import java.util.Date;
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

    public List<Cita> findCitas(Date fechaCita, int idMedico){
        return citaRepository.findByFechaCitaAndIdMedicoIdMedico(fechaCita, idMedico);
    }

    public List<Cita> findMisCitas(int idMedico){
        return citaRepository.findByIdMedicoIdMedico( idMedico );
    }
}

package com.vetun.apirest.service;

import com.vetun.apirest.model.HoraAtencion;
import com.vetun.apirest.model.Medico;
import com.vetun.apirest.repository.HoraAtencionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoraAtencionService {

    private HoraAtencionRepository horaAtencionRepository;

    public HoraAtencionService(HoraAtencionRepository horaAtencionRepository) {
        this.horaAtencionRepository = horaAtencionRepository;
    }

    public HoraAtencion findById(int id){
        return horaAtencionRepository.findById(id).orElse(null);
    }

    public void save(HoraAtencion horaAtencion){
        horaAtencionRepository.save(horaAtencion);
    }

    public List<HoraAtencion> findByMedico(Medico medico){
        return  horaAtencionRepository.findByIdMedico(medico);
    }
}

package com.vetun.apirest.service;

import com.vetun.apirest.model.TipoAtencion;
import com.vetun.apirest.repository.TipoAtencionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAtencionService {
    public TipoAtencionRepository tipoAtencionRepository;

    public TipoAtencionService( TipoAtencionRepository tipoAtencionRepository ){this.tipoAtencionRepository = tipoAtencionRepository;}

    public List<TipoAtencion> findAll(){ return tipoAtencionRepository.findAll();}
}

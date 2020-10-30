package com.vetun.apirest.service;

import com.vetun.apirest.model.Rol;
import com.vetun.apirest.repository.RolRepository;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService( RolRepository rolRepository ){
        this.rolRepository = rolRepository;
    }


    public Rol findById(Integer id ){
        return rolRepository.findById( id ).orElse( null );
    }

}

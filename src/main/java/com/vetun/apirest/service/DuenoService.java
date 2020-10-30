package com.vetun.apirest.service;

import com.vetun.apirest.model.Dueno;
import com.vetun.apirest.pojo.RegistrarDuenoPOJO;
import com.vetun.apirest.repository.DuenoRepository;
import org.springframework.stereotype.Service;

@Service
public class DuenoService {

    private DuenoRepository duenoRepository;

    public DuenoService( DuenoRepository duenoRepository ){
        this.duenoRepository = duenoRepository;
    }

    public Dueno findByCedulaDueno( String cedula ){
        return duenoRepository.findByCedulaDueno( cedula );
    }

    public Dueno findById( Integer id ){
        return duenoRepository.findById( id ).orElse( null );
    }

    public  void save ( Dueno dueno ){
        duenoRepository.save( dueno );
    }

    public boolean isRightDueno( RegistrarDuenoPOJO dueno ){
        boolean correctness = dueno.getNombreDueno() != null && dueno.getApellidoDueno() != null && dueno.getCedulaDueno() != null;
        if( correctness ){
            correctness = !dueno.getNombreDueno().trim( ).isEmpty( )
                    && !dueno.getApellidoDueno().trim( ).isEmpty( )
                    && !dueno.getCedulaDueno().trim( ).isEmpty( );
        }
        return correctness;
    }

    public Dueno mapperDuenoEntity( RegistrarDuenoPOJO DuenoPOJO ){
        Dueno newDueno = new Dueno( );
        newDueno.setNombreDueno( DuenoPOJO.getNombreDueno() );
        newDueno.setApellidoDueno( DuenoPOJO.getApellidoDueno() );
        newDueno.setCedulaDueno( DuenoPOJO.getCedulaDueno() );
        newDueno.setDireccionDueno( DuenoPOJO.getDireccionDueno() );
        newDueno.setTelefonoDueno( DuenoPOJO.getTelefonoDueno() );
        return newDueno;
    }

    public Dueno findByUsuarioIdUsuario(int id){
        return duenoRepository.findByUsuarioIdUsuario(id);
    }

}

package com.vetun.apirest.service;

import com.vetun.apirest.model.Dueno;
import com.vetun.apirest.model.Mascota;
import com.vetun.apirest.pojo.RegistrarDuenoPOJO;
import com.vetun.apirest.pojo.RegistrarMascotaPOJO;
import com.vetun.apirest.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    public MascotaRepository  mascotaRepository;

    public MascotaService( MascotaRepository mascotaRepository ){
        this.mascotaRepository = mascotaRepository;
    }

    public Mascota findById( Integer id ){
        return mascotaRepository.findById( id ).orElse( null );
    }

    public void save( Mascota mascota ){
        mascotaRepository.save( mascota );
    }

    public List<Mascota> findAll(){ return mascotaRepository.findAll();}

    public List<Mascota> findByDueno(int id){
        return mascotaRepository.findByIdDuenoIdDueno(id);
    }

    public Mascota mapperMascotaEntity(RegistrarMascotaPOJO mascotaPOJO ){
        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setEspecie(mascotaPOJO.getEspecie());
        nuevaMascota.setNombreMascota(mascotaPOJO.getNombreMascota());
        nuevaMascota.setRaza(mascotaPOJO.getRaza());
        return nuevaMascota;
    }
}

package com.vetun.apirest.service;

import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.model.Veterinaria;
import com.vetun.apirest.pojo.RegistrarDuenoPOJO;
import com.vetun.apirest.pojo.RegistrarVeterinariaPOJO;
import com.vetun.apirest.repository.VeterinariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinariaService {

    private VeterinariaRepository veterinariaRepository;

    public  VeterinariaService(VeterinariaRepository veterinariaRepository){
        this.veterinariaRepository = veterinariaRepository;
    }

    public Veterinaria findById( Integer id ){
        return veterinariaRepository.findById( id ).orElse(null);
    }

    public Veterinaria findByNombreVeterinaria( String nombre ){ return  veterinariaRepository.findByNombreVeterinaria( nombre );}

    public void save(Veterinaria veterinaria){
        veterinariaRepository.save(veterinaria);
    }

    public List<Veterinaria> findAll(){
        return veterinariaRepository.findAll();
    }

    public Veterinaria mapperVeterinariaEntity(RegistrarVeterinariaPOJO registrarVeterinaria){
        Veterinaria veterinaria = new Veterinaria();

        veterinaria.setNombreVeterinaria(registrarVeterinaria.getNombreVeterinaria());
        veterinaria.setDireccionVeterinaria(registrarVeterinaria.getDireccionVeterinaria());
        veterinaria.setTelefonoVeterinaria(registrarVeterinaria.getTelefonoVeterinaria());
        veterinaria.setTipoVeterinaria(registrarVeterinaria.getTipoVeterinaria());
        return veterinaria;
    }
}

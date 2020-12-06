package com.vetun.apirest.service;

import com.vetun.apirest.model.Medico;
import com.vetun.apirest.pojo.RegistrarMedicoPOJO;
import com.vetun.apirest.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private MedicoRepository medicoRepository;

    public MedicoService( MedicoRepository medicoRepository){
        this.medicoRepository = medicoRepository;
    }

    public Medico findByCedulaMedico(String cedula ){
        return medicoRepository.findByCedulaMedico( cedula );
    }

    public Medico findById( Integer id ){
        return medicoRepository.findById(id).orElse(null);
    }

    public void save ( Medico medico ){
        medicoRepository.save( medico );
    }

    public boolean isRightMedico( RegistrarMedicoPOJO medico ){
        boolean correctness = medico.getNombreMedico() != null && medico.getApellidoMedico() != null && medico.getCedulaMedico() != null;
        if( correctness ){
            correctness = !medico.getNombreMedico().trim( ).isEmpty( )
                    && !medico.getApellidoMedico().trim( ).isEmpty( )
                    && !medico.getCedulaMedico().trim( ).isEmpty( );
        }
        return correctness;
    }

    public Medico mapperMedicoEntity(RegistrarMedicoPOJO medicoPOJO){
        Medico nuevoMedico =  new Medico();
        nuevoMedico.setCedulaMedico(medicoPOJO.getCedulaMedico());
        nuevoMedico.setNombreMedico(medicoPOJO.getNombreMedico());
        nuevoMedico.setApellidoMedico(medicoPOJO.getApellidoMedico());
        nuevoMedico.setDireccionMedico(medicoPOJO.getDireccionMedico());
        nuevoMedico.setTelefonoMedico(medicoPOJO.getTelefonoMedico());
        nuevoMedico.setMatriculaProfesional(medicoPOJO.getMatriculaProfesional());
        return nuevoMedico;
    }

    public Medico findByUsuarioIdUsuario(int id){
        return medicoRepository.findByUsuarioIdUsuario(id);
    }

    public List<Medico> findAll(){
        return medicoRepository.findAll();
    }

    public List<Medico> findByIdVeterinaria(int id) {return medicoRepository.findByIdVeterinariaIdVeterinaria(id);}
}

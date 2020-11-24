package com.vetun.apirest.service;

import com.vetun.apirest.model.ComentarioMedico;
import com.vetun.apirest.repository.ComentarioMedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioMedicoService {
    private ComentarioMedicoRepository comentarioMedicoRepository;

    public ComentarioMedicoService(ComentarioMedicoRepository comentarioMedicoRepository){this.comentarioMedicoRepository = comentarioMedicoRepository;}

    public ComentarioMedico findComentario(Integer iddueno, Integer idmedico) {return comentarioMedicoRepository.findByIdDuenoIdDuenoAndIdMedicoIdMedico(iddueno, idmedico);}

    public List<ComentarioMedico> findComentariosMedico(Integer idmedico){return comentarioMedicoRepository.findByIdMedicoIdMedico(idmedico);}

    public void save( ComentarioMedico comentarioMedico ){
        comentarioMedicoRepository.save( comentarioMedico );
    }
}

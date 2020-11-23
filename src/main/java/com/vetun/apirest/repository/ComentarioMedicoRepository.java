package com.vetun.apirest.repository;

import com.vetun.apirest.model.ComentarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioMedicoRepository extends JpaRepository<ComentarioMedico, Integer> {

    ComentarioMedico findByIdDuenoAndIdMedico(int iddueno, int idmedico);

    List<ComentarioMedico> findByIdMedicoIdMedico(int idmedico);

}

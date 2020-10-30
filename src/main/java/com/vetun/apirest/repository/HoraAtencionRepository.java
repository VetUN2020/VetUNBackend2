package com.vetun.apirest.repository;

import com.vetun.apirest.model.HoraAtencion;
import com.vetun.apirest.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoraAtencionRepository extends JpaRepository<HoraAtencion, Integer> {

    List<HoraAtencion> findByIdMedico(Medico medico);
}

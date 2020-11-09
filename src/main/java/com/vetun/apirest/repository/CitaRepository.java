package com.vetun.apirest.repository;

import com.vetun.apirest.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    //List<Cita> findByFechaCitaBetween(Timestamp a, Timestamp b);

    List<Cita> findByFechaCitaAndIdMedicoIdMedico(Date fechaCita, int idmedico);


}

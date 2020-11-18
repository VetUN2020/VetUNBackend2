package com.vetun.apirest.repository;

import com.vetun.apirest.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findByFechaCitaAndIdMedicoIdMedico(Date fechaCita, int idmedico);

    List<Cita> findByIdMedicoIdMedico(int idMedico);

    List<Cita> findByIdMascotaIdMascota(int idMascota);
}

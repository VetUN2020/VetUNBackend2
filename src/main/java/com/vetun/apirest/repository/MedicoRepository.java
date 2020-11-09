package com.vetun.apirest.repository;

import com.vetun.apirest.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Medico findByCedulaMedico( String cedula );

    Medico findByUsuarioIdUsuario(int id);

    List<Medico> findByIdVeterinariaIdVeterinaria(int id);
}

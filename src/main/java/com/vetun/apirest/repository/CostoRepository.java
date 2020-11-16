package com.vetun.apirest.repository;

import com.vetun.apirest.model.Costo;
import com.vetun.apirest.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostoRepository extends JpaRepository<Costo, Integer> {
    List<Costo> findByIdMedico(Medico idMedico);
}

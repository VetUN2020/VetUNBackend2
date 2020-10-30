package com.vetun.apirest.repository;

import com.vetun.apirest.model.Vacunacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunacionRepository extends JpaRepository<Vacunacion, Integer> {
}

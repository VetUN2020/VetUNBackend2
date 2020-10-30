package com.vetun.apirest.repository;

import com.vetun.apirest.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findAll();

    List<Mascota> findByIdDuenoIdDueno(int idDueno);
}

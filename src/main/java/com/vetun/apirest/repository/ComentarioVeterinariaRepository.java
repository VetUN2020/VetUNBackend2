package com.vetun.apirest.repository;

import com.vetun.apirest.model.ComentarioVeterinaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioVeterinariaRepository extends JpaRepository<ComentarioVeterinaria, Integer> {

    List<ComentarioVeterinaria> findByIdVeterinariaIdVeterinaria (int idveterinaria);

}

package com.vetun.apirest.repository;

import com.vetun.apirest.model.Veterinaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinariaRepository extends JpaRepository<Veterinaria, Integer> {
    Veterinaria findByNombreVeterinaria( String nombre );

}

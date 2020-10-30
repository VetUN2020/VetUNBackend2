package com.vetun.apirest.repository;

import com.vetun.apirest.model.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Integer> {
     Dueno findByCedulaDueno( String cedula );

     Dueno findByUsuarioIdUsuario(int id);
}

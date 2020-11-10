package com.vetun.apirest.repository;

import com.vetun.apirest.model.TipoAtencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoAtencionRepository extends JpaRepository<TipoAtencion, Integer> {

    List<TipoAtencion> findAll();
}

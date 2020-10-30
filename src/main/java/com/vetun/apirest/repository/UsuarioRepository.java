package com.vetun.apirest.repository;

import com.vetun.apirest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername( String username );

    Usuario findByCorreoElectronico( String correo );
}

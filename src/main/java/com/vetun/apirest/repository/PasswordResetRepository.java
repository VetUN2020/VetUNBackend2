package com.vetun.apirest.repository;

import com.vetun.apirest.model.PasswordReset;
import com.vetun.apirest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Integer> {

    PasswordReset findByUsuario(Usuario user);

    PasswordReset findByToken(String token);

    @Transactional
    @Modifying
    @Query("delete from PasswordReset p where p.idToken=:id")
    void deleteToken(@Param("id") int idToken);

}

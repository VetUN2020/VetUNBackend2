package com.vetun.apirest.repository;

import com.vetun.apirest.model.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Integer> {

}

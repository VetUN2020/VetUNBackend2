package com.vetun.apirest.service;

import com.vetun.apirest.model.PasswordReset;
import com.vetun.apirest.repository.PasswordResetRepository;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    private PasswordResetRepository passwordResetRepository;

    public PasswordResetService(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    public void save(PasswordReset passwordReset){
        this.passwordResetRepository.save(passwordReset);
    }
}

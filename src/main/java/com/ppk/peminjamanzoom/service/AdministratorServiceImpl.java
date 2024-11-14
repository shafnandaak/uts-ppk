package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.entity.Administrator;
import com.ppk.peminjamanzoom.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Optional<Administrator> findByEmail(String email) {
        return administratorRepository.findByEmail(email);
    }
}

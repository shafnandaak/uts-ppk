package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.entity.Administrator;

import java.util.Optional;

public interface AdministratorService {
    Optional<Administrator> findByEmail(String email);
}

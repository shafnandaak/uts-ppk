package com.ppk.peminjamanzoom.auth;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public @NotBlank String getEmail() {
        return email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}

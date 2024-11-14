package com.ppk.peminjamanzoom.dto;

import com.ppk.peminjamanzoom.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorDto {
    private Long id;
    private String email;
    private String password;
    private Role roles;
}

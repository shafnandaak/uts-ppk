package com.ppk.peminjamanzoom.mapper;

import com.ppk.peminjamanzoom.dto.AdministratorDto;
import com.ppk.peminjamanzoom.entity.Administrator;
import com.ppk.peminjamanzoom.entity.Role;

public class AdministratorMapper {
    public static AdministratorDto toDto(Administrator admin) {
        if (admin == null) return null;

        return AdministratorDto.builder()
                .id(admin.getId())
//                .username(admin.getUsername())
                .email(admin.getEmail())
                .roles(Role.ADMIN)
                .build();
    }

    public static Administrator toEntity(AdministratorDto dto) {
        if (dto == null) return null;

        return Administrator.builder()
                .id(dto.getId())
//                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .roles(Role.ADMIN)
                .build();
    }
}

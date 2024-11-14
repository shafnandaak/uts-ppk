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
public class MemberDto {
    private Long id;
    private String nim;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}


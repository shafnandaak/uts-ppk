package com.ppk.peminjamanzoom.mapper;

import com.ppk.peminjamanzoom.dto.MemberDto;
import com.ppk.peminjamanzoom.entity.Member;
import com.ppk.peminjamanzoom.entity.Role;

public class MemberMapper {

    public static MemberDto toDto(Member member) {
        if (member == null) return null;

        return MemberDto.builder()
                .id(member.getId())
                .nim(member.getNim())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();  // Roles tidak di-include di sini
    }

    public static Member toEntity(MemberDto memberDto) {
        if (memberDto == null) return null;

        return Member.builder()
                .id(memberDto.getId())
                .nim(memberDto.getNim())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .password(memberDto.getPassword()) // Tambahkan password jika ada
                .roles(Role.MEMBER) // Set role MEMBER di sini juga untuk memastikan konsistensi
                .build();
    }

}


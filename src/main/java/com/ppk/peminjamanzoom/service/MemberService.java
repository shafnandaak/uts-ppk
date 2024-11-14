package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.dto.MemberDto;
import com.ppk.peminjamanzoom.entity.Member;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);  // Untuk membuat member baru
    Member findByEmail(String email);  // Untuk mencari member berdasarkan email
    boolean verifyPassword(String rawPassword, String encodedPassword); // Untuk memverifikasi password yang dimasukkan
    MemberDto updateProfile(MemberDto memberDto); // Untuk mengedit profil member
    void deleteMember(Long memberId); // Untuk menghapus akun member
    void deleteMemberByEmail(String email); // Untuk menghapus akun member berdasarkan email
}

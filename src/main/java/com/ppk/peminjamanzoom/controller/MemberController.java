package com.ppk.peminjamanzoom.controller;

import com.ppk.peminjamanzoom.dto.MemberDto;
import com.ppk.peminjamanzoom.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Endpoint untuk mengedit profil pengguna
    @PutMapping("/profileEdit/{email}")
    public ResponseEntity<Map<String, Object>> updateProfile(@AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser,
                                                             @PathVariable String email,
                                                             @RequestBody MemberDto memberDto) {
        Map<String, Object> response = new HashMap<>();
        String currentEmail = currentUser.getUsername();

        // Validasi akses
        if (!currentEmail.equals(email) && !currentUser.getAuthorities().toString().contains("ROLE_ADMIN")) {
            response.put("success", false);
            response.put("message", "Akses ditolak. Anda tidak memiliki izin untuk mengedit profil ini.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        try {
            MemberDto updatedMember = memberService.updateProfile(memberDto);
            response.put("success", true);
            response.put("message", "Profil berhasil diperbarui.");
            response.put("data", updatedMember);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal memperbarui profil.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint untuk menghapus akun pengguna
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Map<String, Object>> deleteMember(@AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser,
                                                            @PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        String currentEmail = currentUser.getUsername();

        // Validasi akses
        if (!currentEmail.equals(email) && !currentUser.getAuthorities().toString().contains("ROLE_ADMIN")) {
            response.put("success", false);
            response.put("message", "Akses ditolak. Anda tidak memiliki izin untuk menghapus akun ini.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        try {
            memberService.deleteMemberByEmail(email);
            response.put("success", true);
            response.put("message", "Akun berhasil dihapus.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Gagal menghapus akun.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

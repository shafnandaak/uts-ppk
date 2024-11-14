// AuthenticationController.java
package com.ppk.peminjamanzoom.auth;

import com.ppk.peminjamanzoom.dto.AdministratorDto;
import com.ppk.peminjamanzoom.dto.MemberDto;
import com.ppk.peminjamanzoom.service.AdministratorService;
import com.ppk.peminjamanzoom.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid AuthRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            String accessToken = jwtUtil.generateAccessToken(authentication);
            AuthResponse authResponse = new AuthResponse(request.getEmail(), accessToken);

            response.put("success", true);
            response.put("message", "Login berhasil.");
            response.put("data", authResponse);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            response.put("success", false);
            response.put("message", "Login gagal. Kredensial tidak valid.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/registerMember")
    public ResponseEntity<Map<String, Object>> registerMember(@RequestBody @Valid MemberDto memberDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            MemberDto registeredMember = memberService.createMember(memberDto);

            response.put("success", true);
            response.put("message", "Pendaftaran member berhasil.");
            response.put("data", registeredMember);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.put("success", false);
            response.put("message", "Pendaftaran member gagal.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

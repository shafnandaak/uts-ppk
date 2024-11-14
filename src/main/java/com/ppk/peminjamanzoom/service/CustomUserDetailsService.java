package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.entity.Administrator;
import com.ppk.peminjamanzoom.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private AdministratorService administratorService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Cek Member
        try {
            Member member = memberService.findByEmail(email);
            if (member != null) {
                logger.info("Member found: {}, Role: MEMBER", member.getEmail());
                return new org.springframework.security.core.userdetails.User(
                        member.getEmail(),
                        member.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"))
                );
            }
        } catch (Exception e) {
            logger.error("Error checking member: ", e);
        }

        // Cek Administrator
        try {
            Optional<Administrator> adminOptional = administratorService.findByEmail(email);
            if (adminOptional.isPresent()) {
                Administrator admin = adminOptional.get();
                logger.info("Administrator found: {}, Role: ADMIN", admin.getEmail());
                return new org.springframework.security.core.userdetails.User(
                        admin.getEmail(),
                        admin.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                );
            }
        } catch (Exception e) {
            logger.error("Error checking administrator: ", e);
        }

        // Jika tidak ada yang ditemukan
        logger.info("User not found with email: {}", email);
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

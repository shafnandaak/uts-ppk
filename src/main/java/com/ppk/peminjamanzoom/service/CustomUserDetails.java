package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.entity.Administrator;
import com.ppk.peminjamanzoom.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final Member member;
    private final Administrator administrator;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Member member, Collection<? extends GrantedAuthority> authorities) {
        this.member = member;
        this.administrator = null;
        this.authorities = authorities;
    }

    public CustomUserDetails(Administrator administrator, Collection<? extends GrantedAuthority> authorities) {
        this.member = null;
        this.administrator = administrator;
        this.authorities = authorities;
    }

    public Member getMember() {
        return member;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return member != null ? member.getPassword() : administrator.getPassword();
    }

    @Override
    public String getUsername() {
        return member != null ? member.getEmail() : administrator.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Method untuk memeriksa role
    public boolean hasRole(String role) {
        return authorities.stream().anyMatch(authority -> authority.getAuthority().equals(role));
    }
}

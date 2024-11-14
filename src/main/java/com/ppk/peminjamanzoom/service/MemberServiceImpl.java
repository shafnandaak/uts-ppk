package com.ppk.peminjamanzoom.service;

import com.ppk.peminjamanzoom.dto.MemberDto;
import com.ppk.peminjamanzoom.entity.Member;
import com.ppk.peminjamanzoom.entity.Role;
import com.ppk.peminjamanzoom.mapper.MemberMapper;
import com.ppk.peminjamanzoom.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = MemberMapper.toEntity(memberDto);
        member.setRoles(Role.MEMBER);

        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        member = memberRepository.save(member);
        return MemberMapper.toDto(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    @Override
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public MemberDto updateProfile(MemberDto memberDto) {
        // Temukan member berdasarkan email
        Member existingMember = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // Perbarui informasi profil member
        existingMember.setName(memberDto.getName());
        existingMember.setPhoneNumber(memberDto.getPhoneNumber());

        // Jika password diperbarui, lakukan enkripsi kembali
        if (memberDto.getPassword() != null && !memberDto.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
            existingMember.setPassword(encodedPassword);
        }

        memberRepository.save(existingMember);
        return MemberMapper.toDto(existingMember);
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public void deleteMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        memberRepository.delete(member);
    }
}

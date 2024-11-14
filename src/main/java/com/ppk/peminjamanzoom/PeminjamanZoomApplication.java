package com.ppk.peminjamanzoom;

import com.ppk.peminjamanzoom.entity.Administrator;
import com.ppk.peminjamanzoom.entity.Role;
import com.ppk.peminjamanzoom.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PeminjamanZoomApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(PeminjamanZoomApplication.class, args);
    }

    @Bean
    public CommandLineRunner createAdmin(AdministratorRepository adminRepo) {
        return args -> {
            String adminEmail = "admin@example.com";
            if (adminRepo.findByEmail(adminEmail).isEmpty()) {
                Administrator admin = new Administrator();
                //admin.setUsername("admin");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Role.ADMIN);

                adminRepo.save(admin);
                System.out.println("Administrator account created.");
            }
        };
    }
}

package com.spss.glowlytic.config;

import com.spss.glowlytic.entity.Role;
import com.spss.glowlytic.enums.RoleName;
import com.spss.glowlytic.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppInitialConfig {

    private final RoleRepository roleRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            log.info("STARTING TO INIT DATABASE - ROLES...");
            for (RoleName roleName : RoleName.values()) {
                if (roleRepository.findByName(roleName).isEmpty()) {
                    Role role = Role.builder()
                            .name(roleName)
                            .description(getDescription(roleName))
                            .build();
                    roleRepository.save(role);
                    log.info("Initialized role: {}", roleName);
                }
            }
            log.info("DATABASE INITIALIZATION COMPLETED.");
        };
    }

    private String getDescription(RoleName roleName) {
        return switch (roleName) {
            case CUSTOMER -> "Standard customer with basic access rights.";
            case MANAGER -> "Manager with administrative privileges.";
            case STAFF -> "Staff member responsible for daily operations.";
            case COACH -> "Coach/Professional providing guidance.";
        };
    }
}
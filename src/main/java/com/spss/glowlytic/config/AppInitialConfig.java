package com.spss.glowlytic.config;

import com.spss.glowlytic.entity.Role;
import com.spss.glowlytic.enums.RoleName;
import com.spss.glowlytic.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppInitialConfig {

    private final RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            log.info("----- STARTING DATABASE SEEDING -----");
            initRoles();
            log.info("----- DATABASE SEEDING COMPLETED -----");
        };
    }

    private void initRoles() {
        log.info("Seeding Roles...");
        for (RoleName roleName : RoleName.values()) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role role = Role.builder()
                        .name(roleName)
                        .description("Description for " + roleName.name())
                        .build();

                roleRepository.save(role);
                log.info("Initialized role: {}", roleName);
            }
        }
    }


}
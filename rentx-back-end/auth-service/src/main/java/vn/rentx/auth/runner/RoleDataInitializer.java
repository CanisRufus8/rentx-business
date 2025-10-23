package vn.rentx.auth.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity.RoleEntity;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.repository.JPAUserRoleRepository;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleDataInitializer implements CommandLineRunner {

    private final JPAUserRoleRepository roleRepository; // Repository của bạn

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            List<String> defaultRoles = Arrays.asList(
                    "SUPER_ADMIN",
                    "ADMIN",
                    "MANAGER",
                    "LANDLORD",
                    "TENANT",
                    "LEADER",
                    "EMPLOYEE",
                    "USER"
            );

            defaultRoles.forEach(roleName -> {
                RoleEntity role = new RoleEntity();
                role.setName(roleName);
                role.setDescription(roleName + " role");
                roleRepository.save(role);
            });

            System.out.println("✅ Default roles have been inserted into database.");
        } else {
            System.out.println("ℹ️ Roles already exist, skip seeding.");
        }
    }
}

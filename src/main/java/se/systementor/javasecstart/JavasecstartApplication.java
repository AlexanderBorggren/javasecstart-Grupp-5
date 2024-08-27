package se.systementor.javasecstart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.systementor.javasecstart.model.Role;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.repositories.RoleRepository;
import se.systementor.javasecstart.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JavasecstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavasecstartApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if(roleRepository.findByRoleType("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User(1L, "admin", passwordEncoder.encode("admin"), roles);
            userRepository.save(admin);
        };
    }

}

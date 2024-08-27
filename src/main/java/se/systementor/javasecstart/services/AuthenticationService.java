package se.systementor.javasecstart.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.Role;
import se.systementor.javasecstart.model.User;
import se.systementor.javasecstart.repositories.RoleRepository;
import se.systementor.javasecstart.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByRoleType("USER").orElseThrow(() -> new RuntimeException("User role not found"));

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userRepository.save(new User(0L, username, encodedPassword, authorities));
    }
}

package se.systementor.javasecstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.systementor.javasecstart.dtos.UserDTO;
import se.systementor.javasecstart.services.AuthenticationService;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        authenticationService.registerUser(userDTO.getUsername(), userDTO.getPassword());
        return "redirect:/login";
    }
}

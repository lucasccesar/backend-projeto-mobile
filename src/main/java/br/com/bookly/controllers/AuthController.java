package br.com.bookly.controllers;

import br.com.bookly.entities.Users;
import br.com.bookly.entities.dtos.*;
import br.com.bookly.services.TokenService;
import br.com.bookly.services.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersLoginDTO usersLoginDTO) {
        var usernamepassword = new UsernamePasswordAuthenticationToken(usersLoginDTO.email(), usersLoginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamepassword);
        String token = tokenService.generateToken((Users) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> register(@RequestBody UsDTO user) {
        Users registereduser = usersService.signupUser(new Users(user));
        return ResponseEntity.status(201).body(new UsersDTO(registereduser));
    }
}

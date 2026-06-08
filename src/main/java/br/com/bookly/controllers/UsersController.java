package br.com.bookly.controllers;

import br.com.bookly.entities.BookClub;
import br.com.bookly.entities.Users;
import br.com.bookly.entities.dtos.ParticipantUserDTO;
import br.com.bookly.entities.dtos.UsDTO;
import br.com.bookly.entities.dtos.UsersDTO;
import br.com.bookly.entities.dtos.UsersLoginDTO;
import br.com.bookly.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class    UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<Page<UsersDTO>> getUsers(Pageable pageable) {
        Page<UsersDTO> dtos = usersService.getUsers(pageable).map(UsersDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<UsersDTO> signupUser(@RequestBody Users user) {
        Users userSignedUp = usersService.signupUser(user);
        UsersDTO userDTO = new UsersDTO(userSignedUp);
        return ResponseEntity.status(201).body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean deleted = usersService.deleteUser(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable UUID id, @RequestBody Users user) {
        Users updated = usersService.updateUser(id, user);
        UsersDTO userDTO = new UsersDTO(updated);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UsersDTO> loginUser(@RequestBody UsersLoginDTO loginDto) {
        Users user = usersService.loginUser(loginDto.email(),  loginDto.password());
            UsersDTO userDTO = new UsersDTO(user);
            return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok(new UsersDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUser(@PathVariable UUID id) {
        Users user = usersService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsersDTO dto = new UsersDTO(user);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{userId}/favorites/{bookId}")
    public ResponseEntity<?> addFavorite(
            @PathVariable UUID userId,
            @PathVariable UUID bookId) {

        usersService.addFavoriteBook(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/favorites/{bookId}")
    public ResponseEntity<?> removeFavorite(
            @PathVariable UUID userId,
            @PathVariable UUID bookId) {

        usersService.removeFavoriteBook(userId, bookId);
        return ResponseEntity.ok().build();
    }
}

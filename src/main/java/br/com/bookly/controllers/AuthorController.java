package br.com.bookly.controllers;

import br.com.bookly.entities.Author;
import br.com.bookly.entities.dtos.AuthorDTO;
import br.com.bookly.entities.dtos.RatingDTO;
import br.com.bookly.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
   AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody Author author) {
        Author created = authorService.createAuthor(author);
        AuthorDTO authorDTO = new AuthorDTO(created);
        return ResponseEntity.status(201).body(authorDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable UUID id) {
        boolean deleted = authorService.deleteAuthorById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable UUID id, @RequestBody Author author) {
        Author updated = authorService.updateAuthor(id, author);
        AuthorDTO authorDTO = new AuthorDTO(updated);
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable UUID id) {
        Author author = authorService.findAuthorById(id);
        AuthorDTO authorDTO = new AuthorDTO(author);
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping("/name")
    public ResponseEntity<Page<AuthorDTO>> findAuthorByName(@RequestParam String name, Pageable pageable) {
        Page<AuthorDTO> authors = authorService.findAuthorByName(name, pageable).map(AuthorDTO::new);
        if (authors == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authors);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorDTO>> listAllAuthors(Pageable pageable) {
        Page<AuthorDTO> authors = authorService.findAllAuthors(pageable).map(AuthorDTO::new);
        return ResponseEntity.ok(authors);
    }
}
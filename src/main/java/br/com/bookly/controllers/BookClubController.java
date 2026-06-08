package br.com.bookly.controllers;


import br.com.bookly.entities.BookClub;
import br.com.bookly.entities.dtos.BookClubDTO;
import br.com.bookly.services.BookClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookclub")
public class BookClubController {

    @Autowired
    BookClubService bookClubService;

    @GetMapping
    public ResponseEntity<Page<BookClubDTO>> listBookClubs(Pageable pageable) {
        Page<BookClubDTO> dtos = bookClubService.listBookClub(pageable).map(BookClubDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<BookClubDTO> findBookClubById(@PathVariable UUID id) {
        BookClub bookClub = bookClubService.findBookClubById(id);
        return ResponseEntity.ok(new BookClubDTO(bookClub));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BookClubDTO> findBookClubByName(@PathVariable("name") String name) {
        BookClub bookClub = bookClubService.findBookClubByName(name);
        return ResponseEntity.ok(new BookClubDTO(bookClub));
    }


    @PostMapping
    public ResponseEntity<BookClubDTO> createBookClub(@RequestBody BookClub bookClub) {
        BookClub createdBookClub = bookClubService.createBookClub(bookClub);
        BookClubDTO dto = new BookClubDTO(createdBookClub);
            return ResponseEntity.status(201).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookClubDTO> updateBookClub(@PathVariable UUID id, @RequestBody BookClub bookClub) {
        BookClub updated = bookClubService.updateBookClub(id, bookClub);
        BookClubDTO dto = new BookClubDTO(updated);
            return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookClubById(@PathVariable UUID id) {
        boolean deleted = bookClubService.deleteBookClubById(id);
            return ResponseEntity.ok().build();
    }
}

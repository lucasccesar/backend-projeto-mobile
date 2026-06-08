package br.com.bookly.controllers;

import br.com.bookly.entities.BookClubAssignment;
import br.com.bookly.entities.dtos.BookClubAssignmentDTO;
import br.com.bookly.entities.dtos.ClubMessageDTO;
import br.com.bookly.services.BookClubAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookclubassignment")
public class BookClubAssignmentController {

    @Autowired
    BookClubAssignmentService bookClubAssignmentService;

    @GetMapping
    public ResponseEntity<Page<BookClubAssignmentDTO>> listBookClubAssignments(Pageable pageable) {
        Page<BookClubAssignmentDTO> dto = bookClubAssignmentService.findAllBookClubsAssignment(pageable).map(BookClubAssignmentDTO::new);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookClubAssignmentDTO> findBookClubAssignmentById(@PathVariable UUID id) {
        BookClubAssignment bookClubAssignment = bookClubAssignmentService.findBookClubAssignmentById(id);
            return ResponseEntity.ok(new BookClubAssignmentDTO(bookClubAssignment));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Page<BookClubAssignmentDTO>> getAssignmentsByBook(@PathVariable UUID bookId, Pageable pageable) {
        Page<BookClubAssignmentDTO> dtoPage = bookClubAssignmentService.findByBookId(bookId, pageable).map(BookClubAssignmentDTO::new);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<Page<BookClubAssignmentDTO>> getAssignmentsByClub(@PathVariable UUID clubId, Pageable pageable) {
        Page<BookClubAssignmentDTO> dtoPage = bookClubAssignmentService.findByBookClubId(clubId, pageable).map(BookClubAssignmentDTO::new);
        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping
    public ResponseEntity<BookClubAssignmentDTO> createBookClubAssignment(@RequestBody BookClubAssignment bookClubAssignment) {
        BookClubAssignment created = bookClubAssignmentService.createBookClubAssignment(bookClubAssignment);
            BookClubAssignmentDTO dto = new BookClubAssignmentDTO(created);
            return ResponseEntity.status(201).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookClubAssignmentDTO> updateBookClubAssignment(@PathVariable UUID id, @RequestBody BookClubAssignment bookClubAssignment) {
        BookClubAssignment updated = bookClubAssignmentService.updateBookClubAssignment(id, bookClubAssignment);
            return ResponseEntity.ok(new BookClubAssignmentDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookClubAssignmentById(@PathVariable UUID id) {
        boolean deleted = bookClubAssignmentService.deleteById(id);
            return ResponseEntity.ok().build();
    }
}

package br.com.bookly.controllers;

import br.com.bookly.entities.ParticipantUser;
import br.com.bookly.entities.dtos.ParticipantUserDTO;
import br.com.bookly.services.ParticipantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/participantuser")
public class ParticipantUserController {

    @Autowired
    ParticipantUserService participantUserService;

    @GetMapping
    public ResponseEntity<Page<ParticipantUserDTO>> getParticipantsUsers(Pageable pageable) {
        Page<ParticipantUserDTO> dtos = participantUserService.getParticipantUsers(pageable).map(ParticipantUserDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantUserDTO> getParticipantById(@PathVariable UUID id) {
        ParticipantUser participant = participantUserService.getParticipantUser(id);
            return ResponseEntity.ok(new ParticipantUserDTO(participant));
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<Page<ParticipantUserDTO>> getByUserId(@PathVariable UUID userId, Pageable pageable) {
        Page<ParticipantUserDTO> dtos = participantUserService.getByUserId(userId, pageable).map(ParticipantUserDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/byclub/{clubId}")
    public ResponseEntity<Page<ParticipantUserDTO>> getByClubId(@PathVariable UUID clubId, Pageable pageable) {
        Page<ParticipantUserDTO> dtos = participantUserService.getByClubId(clubId, pageable).map(ParticipantUserDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ParticipantUserDTO> createParticipantUser(@RequestBody ParticipantUser participantUser) {
        ParticipantUser created = participantUserService.createParticipantUser(participantUser);
            ParticipantUserDTO dto = new ParticipantUserDTO(created);
            return ResponseEntity.status(201).body(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ParticipantUserDTO> updateParticipant(@PathVariable UUID id, @RequestBody ParticipantUser participantUser) {
        ParticipantUser updated = participantUserService.updateParticipantUser(id, participantUser);
            ParticipantUserDTO dto = new ParticipantUserDTO(updated);
            return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        boolean deleted = participantUserService.deleteParticipantUserById(id);
            return ResponseEntity.ok().build();
    }
}



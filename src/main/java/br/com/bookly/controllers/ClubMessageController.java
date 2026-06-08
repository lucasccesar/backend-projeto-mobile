package br.com.bookly.controllers;


import br.com.bookly.entities.ClubMessage;
import br.com.bookly.entities.dtos.ClubMessageDTO;
import br.com.bookly.services.ClubMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clubmessage")
public class ClubMessageController {

    @Autowired
    ClubMessageService clubMessageService;

    @GetMapping
    ResponseEntity<Page<ClubMessageDTO>> listClubMessages(Pageable pageable) {
        Page<ClubMessageDTO> messagesDto = clubMessageService.listClubMessage(pageable).map(ClubMessageDTO::new);
        return ResponseEntity.ok().body(messagesDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<ClubMessageDTO> findClubMessageById(@PathVariable UUID id) {
        ClubMessage clubMessage = clubMessageService.findClubMessageById(id);
            return ResponseEntity.ok(new ClubMessageDTO(clubMessage));
    }

    @GetMapping("/club/{clubId}")
    public ResponseEntity<Page<ClubMessageDTO>> findClubMessageByClubId(@PathVariable UUID clubId, Pageable pageable) {
        Page<ClubMessageDTO> messagesDto = clubMessageService.getByClubId(clubId, pageable).map(ClubMessageDTO::new);
        return ResponseEntity.ok().body(messagesDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ClubMessageDTO>> findClubMessageByUserId(@PathVariable UUID userId, Pageable pageable) {
        Page<ClubMessageDTO> messagesDto = clubMessageService.getByUserId(userId, pageable).map(ClubMessageDTO::new);
        return ResponseEntity.ok().body(messagesDto);
    }

    @PostMapping
    public ResponseEntity<ClubMessageDTO> createClubMessage(@RequestBody ClubMessage clubMessage) {
        ClubMessage created = clubMessageService.createClubMessage(clubMessage);
            ClubMessageDTO dto = new ClubMessageDTO(created);
            return ResponseEntity.status(201).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubMessageDTO> updateClubMessage(@PathVariable UUID id, @RequestBody ClubMessage clubMessage){
        ClubMessage updated = clubMessageService.updateClubMessage(id, clubMessage);
        ClubMessageDTO dto = new ClubMessageDTO(updated);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClubMessage> deleteClubMessage(@PathVariable UUID id){
        boolean deleted = clubMessageService.deleteClubMessageById(id);
            return ResponseEntity.ok().build();
    }
}

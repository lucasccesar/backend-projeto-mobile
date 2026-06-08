package br.com.bookly.controllers;

import br.com.bookly.entities.Colection;
import br.com.bookly.entities.dtos.ColectionDTO;
import br.com.bookly.entities.dtos.ColectionRespondeDTO;
import br.com.bookly.services.ColectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/colection") //
public class ColectionController {

    @Autowired
    private ColectionService colectionService;

    @PostMapping
    public ResponseEntity<ColectionRespondeDTO> createColection(@RequestBody ColectionDTO colectionDTO) {
        Colection createdCollection = colectionService.createColection(colectionDTO);
        ColectionRespondeDTO cd = new ColectionRespondeDTO(createdCollection);
        return ResponseEntity.status(201).body(cd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColectionById(@PathVariable UUID id) {
        boolean deleted = colectionService.deleteColectionById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColectionRespondeDTO> updateColection(@PathVariable UUID id, //O Path Variable (id) identifica o recurso
                                                      @RequestBody Colection colection){ // o Request Body (colection) provê os novos dados.
        Colection updatedCollection = colectionService.updateColection(id, colection);
        ColectionRespondeDTO cd = new ColectionRespondeDTO(updatedCollection);
        return ResponseEntity.ok(cd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColectionRespondeDTO> findColectionById(@PathVariable UUID id) {
        Colection colection = colectionService.findColectionById(id);
        return ResponseEntity.ok(new ColectionRespondeDTO(colection));
    }

    @GetMapping("/name")
    public ResponseEntity<ColectionRespondeDTO> findColectionByName(@RequestParam String name) {
        Colection colection = colectionService.findColectionByName(name);
        return ResponseEntity.ok(new ColectionRespondeDTO(colection));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ColectionRespondeDTO>> findColectionsByIdUser(@PathVariable UUID userId, Pageable pageable) {
        Page<ColectionRespondeDTO> colection = colectionService.findByUser_Id(userId, pageable).map(ColectionRespondeDTO::new);
        return ResponseEntity.ok(colection);
    }


    @GetMapping
    public ResponseEntity<Page<ColectionRespondeDTO>> listAllColections(Pageable pageable) {
        Page <ColectionRespondeDTO> ct = colectionService.findAllColections(pageable);
        return ResponseEntity.ok(ct);
    }
}
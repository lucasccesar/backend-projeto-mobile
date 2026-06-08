package br.com.bookly.controllers;

import br.com.bookly.entities.Purchase;
import br.com.bookly.entities.PurchaseBook;
import br.com.bookly.entities.dtos.PurchaseDTO;
import br.com.bookly.entities.dtos.PurchaseResponseDto;
import br.com.bookly.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponseDto> createPurchase(@RequestBody PurchaseDTO purchasedto) {
        Purchase createdPurchase = purchaseService.createPurchase(purchasedto);
        PurchaseResponseDto pg  = new PurchaseResponseDto(createdPurchase);
        return ResponseEntity.status(201).body(pg);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <PurchaseResponseDto> deletePurchase(@PathVariable UUID id) {
        boolean deleted = purchaseService.deletePurchase(id);
            return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseResponseDto> updatePurchase(@PathVariable UUID id, @RequestBody PurchaseDTO purchase) {

        Purchase updatedPurchase = purchaseService.updatePurchase(id, purchase);
        PurchaseResponseDto pg  = new PurchaseResponseDto(updatedPurchase);
        return ResponseEntity.ok(pg);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<PurchaseResponseDto> GetfindById(@PathVariable UUID id) {
        Purchase purchase = purchaseService.findPurchaseById(id);
        PurchaseResponseDto pg  = new PurchaseResponseDto(purchase);
        return ResponseEntity.ok(pg);

    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Page<PurchaseResponseDto>> findPurchaseByDate_purchaseDate(@PathVariable("date")
                                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Pageable pageable) {
        Page<PurchaseResponseDto> purchase = purchaseService.findPurchaseByDate_purchaseDate(date, pageable);
        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/PageId/{id}")
    public ResponseEntity<Page<PurchaseResponseDto>> findPurchaseByUser_id(@PathVariable UUID id, Pageable pageable) {
        Page<PurchaseResponseDto> purchase = purchaseService.findPurchaseByUser_id(id, pageable);
        return ResponseEntity.ok(purchase);
    }

    @GetMapping
    public ResponseEntity<Page<PurchaseResponseDto>> findAll(Pageable pageable) {
        Page<PurchaseResponseDto> page = purchaseService.findAllPurchase(pageable);
        return ResponseEntity.ok(page);
    }
}

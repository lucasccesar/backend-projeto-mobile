package br.com.bookly.services;

import br.com.bookly.entities.Purchase;
import br.com.bookly.entities.dtos.PurchaseDTO;
import br.com.bookly.entities.dtos.PurchaseResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface PurchaseService {
    public Purchase createPurchase(PurchaseDTO purchaseDTO);
    public boolean deletePurchase(UUID id);
    public Purchase updatePurchase(UUID id,PurchaseDTO purchase);
    public Purchase findPurchaseById(UUID id);
    public Page<PurchaseResponseDto> findPurchaseByUser_id(UUID id, Pageable pageable);
    public Page<PurchaseResponseDto> findAllPurchase(Pageable pageable);
    public Page<PurchaseResponseDto> findPurchaseByDate_purchaseDate(LocalDate date, Pageable pageable);
}
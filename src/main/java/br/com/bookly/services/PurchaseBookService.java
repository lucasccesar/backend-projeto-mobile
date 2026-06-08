package br.com.bookly.services;


import br.com.bookly.entities.PurchaseBook;

import br.com.bookly.entities.dtos.PurchaseBookResponseDTO;
import br.com.bookly.repositories.PurchaseBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface PurchaseBookService {

    PurchaseBookRepository getPurchaseBookRepository();
    public PurchaseBook findPurchaseBook(UUID id);
    public PurchaseBook findPurchaseBookByPurchase_IdPurchase(UUID idPurchase);
    public Page<PurchaseBookResponseDTO> findPurchaseBookByBook_IdBook(UUID idbook, Pageable pageable);
    public Page<PurchaseBookResponseDTO> findByPurchase_IdPurchase(UUID idPurchase, Pageable pageable);

}

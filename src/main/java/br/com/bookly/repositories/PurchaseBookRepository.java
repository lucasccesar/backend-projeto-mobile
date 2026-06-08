package br.com.bookly.repositories;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.PurchaseBook;
import br.com.bookly.entities.dtos.PurchaseBookDTO;
import br.com.bookly.entities.dtos.PurchaseBookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseBookRepository extends JpaRepository<PurchaseBook, UUID> {
    public PurchaseBook findPurchaseBookByPurchase_IdPurchase(UUID idPurchase);
    public Page<PurchaseBook> findPurchaseBookByBook_IdBook(UUID idbook,  Pageable pageable);
    public Page<PurchaseBook> findByPurchase_IdPurchase(UUID idPurchase, Pageable pageable);
}

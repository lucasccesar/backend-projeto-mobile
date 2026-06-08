package br.com.bookly.entities.dtos;

import br.com.bookly.entities.Purchase;
import br.com.bookly.entities.PurchaseBook;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter

public class PurchaseResponseDto {

    private UUID idUser;
    private BigDecimal totalValuation;
    private LocalDate purchaseDate;
    private List<PurchaseBookResponseDTO> books;

    public PurchaseResponseDto(Purchase purchase) {
        this.idUser = purchase.getUser().getId();
        this.totalValuation = purchase.getTotalValuation();
        this.purchaseDate = purchase.getPurchaseDate();
        this.books = purchase.getPurchaseBooks().stream().map(PurchaseBookResponseDTO::new).toList();
    }
}

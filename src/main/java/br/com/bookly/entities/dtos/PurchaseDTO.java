package br.com.bookly.entities.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseDTO {

    private UUID idUser;
    private BigDecimal totalValuation;
    private LocalDate purchaseDate;
    private List<PurchaseBookDTO> books;

    public PurchaseDTO(UUID idUser, BigDecimal totalValuation, LocalDate purchaseDate, List<PurchaseBookDTO> books) {
        this.idUser = idUser;
        this.totalValuation = totalValuation;
        this.purchaseDate = purchaseDate;
        this.books = books;
    }
}

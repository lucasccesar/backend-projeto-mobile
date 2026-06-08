package br.com.bookly.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
public class PurchaseBookDTO {

    //DTO para a entrada
    private UUID idBook;
    private UUID purchase;
    private BigDecimal amount;
    private Integer quantity;
    private BigDecimal unitPrice;

    public PurchaseBookDTO() {
    }

    public PurchaseBookDTO(UUID idBook, UUID purchase, Integer quantity,  BigDecimal unitPrice) {
        this.idBook = idBook;
        this.purchase = purchase;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}

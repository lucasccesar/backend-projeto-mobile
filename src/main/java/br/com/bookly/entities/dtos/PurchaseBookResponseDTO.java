package br.com.bookly.entities.dtos;

import br.com.bookly.entities.PurchaseBook;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PurchaseBookResponseDTO {

    //DTO para a saída
    private UUID idPurchaseBook;
    private UUID idBook;
    private UUID idPurchase;
    private BigDecimal unitPrice;
    private Integer quantity;

    public PurchaseBookResponseDTO(PurchaseBook pb) {
        this.idPurchaseBook = pb.getIdPurchaseBook(); // se você tem o id da entidade
        this.idBook = pb.getBook().getIdBook();
        this.idPurchase = pb.getPurchase().getIdPurchase();
        this.unitPrice = pb.getUnitPrice();
        this.quantity = pb.getQuantity();
    }
}

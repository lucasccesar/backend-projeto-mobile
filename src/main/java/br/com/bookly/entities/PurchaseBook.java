package br.com.bookly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Compra_Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class PurchaseBook {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_compra_livro")
    private UUID idPurchaseBook;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_compra", nullable = true)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    private Book book;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity;

    @Column(name = "preco_unidade",nullable = false, columnDefinition = "NUMERIC(10,2)")
    private BigDecimal unitPrice;
}

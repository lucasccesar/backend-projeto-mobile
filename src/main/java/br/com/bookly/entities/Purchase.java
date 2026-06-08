package br.com.bookly.entities;

import br.com.bookly.entities.dtos.PurchaseBookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Compra")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_compra")
    private UUID idPurchase;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Users user;

    @Column(name = "data_compra",nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "valor_total",nullable = false, columnDefinition = "NUMERIC(10,2)")
    private BigDecimal totalValuation;

    // Relação de Compra com CompraLivro (PurchaseBook)
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseBook> purchaseBooks = new ArrayList<>();

}

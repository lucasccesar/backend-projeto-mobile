package br.com.bookly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence. *;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mensagem_clube")
public class ClubMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_mensagem_clube")
    private UUID idClubMessage;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id_clube_do_livro", nullable = false)
    private BookClub club;

    @Column(nullable = false, length = 512, name = "conteudo_mensagem")
    private String message;

    @Column(nullable = false, name = "data_mensagem")
    private Timestamp messageDate;
}

package br.com.bookly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence. *;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuario_participante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario_participante")
    private UUID idParticipantUser;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id_clube_do_livro", nullable = false)
    private BookClub club;

    @Column(nullable = false, name = "data_entrada")
    private LocalDate entryDate;

}

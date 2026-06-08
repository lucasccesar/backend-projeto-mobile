package br.com.bookly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence. *;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clube_do_livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookClub { // clube_do_livro

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_clube_do_livro")
    private UUID idBookClub;

    @Column(name = "nome",nullable = false)
    private String name;

    @Column(name = "tema")
    private String theme;

    @Column(name = "descricao",nullable = false, length = 512)
    private String description;

    // Relação de clubeDoLivro com LivroClube (ClubBook)
    @OneToMany(mappedBy = "bookClub", cascade = CascadeType.ALL, orphanRemoval = true) //COMPARAR!!!!
    private List<BookClubAssignment> MensalBooks = new ArrayList<>();

    // Relação ClubeDoLivro com MensagemClube (ClubMessage)
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubMessage> ClubMessage = new ArrayList<>();

    // Relação ClubeDoLivro com UsuariosParticipantes (ParticipantUser)
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipantUser> UsersParticipants = new ArrayList<>();
}

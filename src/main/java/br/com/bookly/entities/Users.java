package br.com.bookly.entities;

import br.com.bookly.entities.Enums.UserType;
import br.com.bookly.entities.dtos.UsDTO;
import br.com.bookly.entities.dtos.UsersRegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Usuarios")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate birthday;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "senha", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private UserType type;

    // relação de usuário com ClubMessage
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubMessage> MensagensDoUsuario = new ArrayList<>();

    // relação de usuário com ParticipantUser
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipantUser> usuariosParticipantes = new ArrayList<>();

    // relação de usuário com avaliação (Rating)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "livros_favoritos",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_livro")
    )
    private Set<Book> favoriteBooks = new HashSet<>();


    public Users(UsersRegisterDTO usersRegisterDTO) {
        this.name = usersRegisterDTO.name();
        this.email = usersRegisterDTO.email();
        this.password = usersRegisterDTO.password();
        this.birthday = usersRegisterDTO.birthday();
        this.type = usersRegisterDTO.userType();
    }

    public Users(UsDTO user) {
        this.name = user.name();
        this.email = user.email();
        this.password = user.password();
        this.birthday = user.birthday();
        this.type = user.userType();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.type == UserType.ADMINISTRATOR) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"),
                    new SimpleGrantedAuthority("ROLE_CLIENT")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
package br.com.bookly.services;


import br.com.bookly.entities.Users;
import br.com.bookly.repositories.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;
import java.util.UUID;

public interface UsersService {

    UsersRepository  getUsersRepository();
    Users signupUser(Users user);
    Users loginUser(String email, String password);
    Users updateUser(UUID id, Users user);
    boolean deleteUser(UUID id);
    Page<Users> getUsers(Pageable pageable);
    Optional<Users> getUserById(UUID id);
    public Users getUserByEmail(String email);
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException;

    void addFavoriteBook(UUID userId, UUID bookId);
    void removeFavoriteBook(UUID userId, UUID bookId);
}

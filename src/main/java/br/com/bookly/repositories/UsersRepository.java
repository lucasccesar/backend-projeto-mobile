package br.com.bookly.repositories;

import br.com.bookly.entities.Enums.UserType;
import br.com.bookly.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    public List<Users> findAll();
    public Users findByEmail(String email);
    public Users findByName(String name);
    public boolean existsByEmail(String email);
    public List<Users> findByNameContainingIgnoreCase(String name);
    public List<Users> findByType(UserType type);
    public Optional<Users> findById(UUID id);
    public void deleteById(UUID id);
    public boolean existsByIdAndType(UUID id, UserType type);
}

package br.com.bookly.services.impl;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.Enums.UserType;
import br.com.bookly.entities.Users;
import br.com.bookly.exceptions.*;
import br.com.bookly.repositories.BookRepository;
import br.com.bookly.repositories.UsersRepository;
import br.com.bookly.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    @Override
    public Users signupUser(Users user) {

        if(user.getName() == null || user.getName().isBlank()) {
            throw new BadRequestException("Error: Username not must be null or blank");
        }

        if(user.getEmail() == null || user.getEmail().isBlank() ) {
            throw new BadRequestException("Error: Email not must be null or blank");
        }

        if(usersRepository.findByEmail(user.getEmail()) != null){
            throw new ExistentEmailException("Error: User with this Email already exists");
        }

        if(user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BadRequestException("Error: Password not must be null or blank");
        }

        if(user.getBirthday().isAfter(LocalDate.now())) {
            throw new invalidDateException("Error: Birthday not after now");
        }

        if(user.getType() == null) {
            user.setType(UserType.CLIENT);
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return usersRepository.save(user);
    }

    @Override
    public Users loginUser(String email, String password) {

        Users userByEmail = usersRepository.findByEmail(email);

        if(email == null || email.isBlank()) {
            throw new BadRequestException("Error: Email can't be null");
        }

        if(password == null || password.isBlank()) {
            throw new BadRequestException("Error: Password can't be null");
        }

        if(userByEmail == null) {
            throw new InexistentUserByEmailException("Error: Inexistent user with email " + email);
        }

        if(!userByEmail.getEmail().equals(email)) {
            throw new DifferentException("Error: Email not match");
        }

        if(!passwordEncoder.matches(password, userByEmail.getPassword())) {
            throw new DifferentException("Error: Password not match");
        }

        return userByEmail;

    }

    @Override
    public Users updateUser(UUID id, Users user) {

        if(user == null){
            throw new BadRequestException("Error: User not must be null ");
        }

        if(id==null){
            throw new BadRequestException("Error: ID not must be null ");
        }

        Users userById = usersRepository.findById(id).orElse(null);

        if(userById == null) {
            throw new InexistentIdUserException("Error: User with this id not found") ;
        }

        userById.setName(user.getName());

        boolean isSameEmail = user.getEmail().equals(userById.getEmail());

        if(user.getName() == null || user.getName().isBlank()) {
            throw new BadRequestException("Error: Name not must be null or blank");
        }

        if(user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BadRequestException("Error: Password not must be null or blank");
        }

        if(!isSameEmail) {
            if(user.getEmail() == null || user.getEmail().isBlank()){
                throw new BadRequestException("Error: Email not must be null or blank");
            }
            if(usersRepository.existsByEmail(user.getEmail())){
                throw new ExistentEmailException("Error: Email already exists");
            }
            userById.setEmail(user.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        userById.setPassword(hashedPassword);

        if (user.getType() != null) {
            userById.setType(user.getType());
        }

        return usersRepository.save(userById);

    }

    @Override
    public boolean deleteUser(UUID id) {
        if(!usersRepository.existsById(id)){
            throw new InexistentIdUserException("Error: User with this id not found");
        }

        usersRepository.deleteById(id);
        return true;
    }


    @Override
    public Page<Users> getUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> getUserById(UUID id) {
        return usersRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Error: User not found with email " + email);
        }
        return user;
    }

    @Override
    public void addFavoriteBook(UUID userId, UUID bookId) {
        Users user = usersRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        user.getFavoriteBooks().add(book);
        usersRepository.save(user);
    }

    @Override
    public void removeFavoriteBook(UUID userId, UUID bookId) {
        Users user = usersRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        user.getFavoriteBooks().remove(book);
        usersRepository.save(user);
    }
}

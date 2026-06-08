package br.com.bookly.services;

import br.com.bookly.entities.Users;

public interface TokenService {
    public String generateToken(Users user);
    public String validateToken(String token);
}

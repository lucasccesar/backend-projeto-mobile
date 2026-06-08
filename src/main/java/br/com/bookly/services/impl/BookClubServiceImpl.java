package br.com.bookly.services.impl;

import br.com.bookly.entities.BookClub;
import br.com.bookly.exceptions.BadRequestException;
import br.com.bookly.exceptions.ExistentBookClubException;
import br.com.bookly.exceptions.InexistentBookClubException;
import br.com.bookly.repositories.BookClubRepository;
import br.com.bookly.services.BookClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class BookClubServiceImpl implements BookClubService {

    @Autowired
    BookClubRepository bookClubRepository;

    @Override
    public BookClub createBookClub(BookClub bookClub) {

        if (bookClub.getName() == null || bookClub.getName().isBlank()) {
            throw new BadRequestException("Error: BookClub name is required");
        }

        if (bookClub.getTheme() == null || bookClub.getTheme().isBlank()) {
            throw new BadRequestException("Error: BookClub theme is required");
        }

        // descrição não obrigatoria seta como vazio
        if (bookClub.getDescription() == null || bookClub.getDescription().isBlank()) {
            bookClub.setDescription("");
        }

        // verifica se já tem um clube com o mesmo nome
        if (bookClubRepository.existsByNameIgnoreCase(bookClub.getName())) {
            throw new ExistentBookClubException("Error: BookClub already exists, try another name!");
        }

        return bookClubRepository.save(bookClub);
    }

    @Override
    public BookClub updateBookClub(UUID id, BookClub bookClub) {

        BookClub exists = bookClubRepository.findById(id).orElse(null); // se o clube não existir retorna null

        if (exists == null) {
            throw new InexistentBookClubException("Error: BookClub Not Found");
        }

        if (bookClub.getName() == null || bookClub.getName().isBlank()) {
            throw new BadRequestException("Error: BookClub name is required");
        }

        if (bookClub.getTheme() == null || bookClub.getTheme().isBlank()) {
            throw new BadRequestException("Error: BookClub theme is required");
        }

        // Se o nome foi alterado, verifica duplicação
        if (!exists.getName().equals(bookClub.getName()) // verifica se foi feita alteração no nome
                && bookClubRepository.existsByNameIgnoreCase(bookClub.getName()))// verifica se existe algum clube com esse nome
            {throw new ExistentBookClubException("Error: BookClub already exists, try another name!");}


        exists.setName(bookClub.getName());
        exists.setTheme(bookClub.getTheme());
        exists.setDescription(bookClub.getDescription());

        return bookClubRepository.save(exists);
    }

    @Override
    public boolean deleteBookClubById(UUID id) {

        if (bookClubRepository.existsById(id) == false) {
            throw new InexistentBookClubException("Error: BookClub Not Found");
        }

        bookClubRepository.deleteById(id);
        return true;
    }

    @Override
    public BookClub findBookClubById(UUID id) {
        return bookClubRepository.findById(id)
                .orElseThrow(() ->  new InexistentBookClubException("Error: BookClub Not Found with this id"));
    }

    @Override
    public BookClub findBookClubByName(String name) {
        BookClub bookClub = bookClubRepository.findBookClubByNameIgnoreCase(name);
        if(bookClub == null) {
            throw new InexistentBookClubException("Error: BookClub Not Found with this name");
        }
        return bookClub;
    }

    @Override
    public Page<BookClub> listBookClub(Pageable pageable) {
        return bookClubRepository.findAll(pageable);
    }
}
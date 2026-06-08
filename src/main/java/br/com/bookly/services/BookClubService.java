package br.com.bookly.services;

import br.com.bookly.entities.BookClub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface BookClubService {

    public BookClub createBookClub(BookClub bookClub);
    public boolean deleteBookClubById(UUID id);
    public BookClub updateBookClub(UUID id, BookClub bookClub);
    public BookClub findBookClubById(UUID id);
    public BookClub findBookClubByName(String name);
    public Page<BookClub> listBookClub(Pageable pageable);
}

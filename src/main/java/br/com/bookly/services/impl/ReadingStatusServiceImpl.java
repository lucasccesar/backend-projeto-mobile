package br.com.bookly.services.impl;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.ReadingStatus;
import br.com.bookly.entities.Users;
import br.com.bookly.exceptions.*;
import br.com.bookly.repositories.ReadingStatusRepository;
import br.com.bookly.services.UsersService;
import br.com.bookly.services.BookService;
import br.com.bookly.services.ReadingStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReadingStatusServiceImpl implements ReadingStatusService {

    @Autowired
    ReadingStatusRepository readingStatusRepository;

    @Autowired
    BookService bookService;

    @Autowired
    UsersService usersService;


    @Override
    public ReadingStatus createReadingStatus(ReadingStatus readingStatus) {
        if(readingStatus == null || readingStatus.getStatus() == null){
            throw new InvalidReadingStatusException("Error: Null or Invalid Reading Status");
        }

        Users user = usersService.getUsersRepository().findById(readingStatus.getUsers().getId()).orElse(null);
        if(user == null){
            throw new InexistentIdUserException("Error: User not found");
        }

        Book book = bookService.getBookRepository().findById(readingStatus.getBook().getIdBook()).orElse(null);
        if(book == null){
            throw new InexistentBookException("Error: Book not found");
        }

        UUID userId = readingStatus.getUsers().getId();
        UUID bookId = readingStatus.getBook().getIdBook();

        ReadingStatus existing = readingStatusRepository.findByBook_IdBookAndUsers_Id(bookId, userId);

        if (existing != null) {
            existing.setStatus(readingStatus.getStatus());
            return readingStatusRepository.save(existing);
        }

        ReadingStatus rd = new ReadingStatus();
        rd.setStatus(readingStatus.getStatus());
        rd.setBook(book);
        rd.setUsers(user);

        return readingStatusRepository.save(rd);

    }

    @Override
    public ReadingStatus updateReadingStatus(UUID id, ReadingStatus readingStatus) {

        ReadingStatus exists = readingStatusRepository.findByIdReadingStatus(id);
        if(exists==null){
            throw new ReadingStatusNotFoundException("Error: Reading Status Not Found");
        }

        if(readingStatus.getBook() == null){
            throw new InvalidReadingStatusException("Error: Null Book List");
        }
        if(readingStatus.getStatus() == null){
            throw new InvalidReadingStatusException("Error: Null Reading Status");
        }
        if(readingStatus.getStatus().equals(exists.getStatus())){
            throw new InvalidReadingStatusException("Error: Same Reading Status");
        }

        exists.setStatus(readingStatus.getStatus());
        return readingStatusRepository.save(exists);

    }

    @Override
    public boolean deleteReadingStatus(UUID readingStatusId) {
        if(readingStatusRepository.existsById(readingStatusId) == false){
            throw new ReadingStatusNotFoundException("Error: Reading Status Not Found");
        }

        readingStatusRepository.deleteById(readingStatusId);
        return true;
    }

    @Override
    public ReadingStatus findReadingStatus(UUID readingStatusId) {
        ReadingStatus readingStatus = readingStatusRepository.findById(readingStatusId).orElse(null);
        if(readingStatus==null){
            throw new ReadingStatusNotFoundException("Error: Reading Status Not Found");
        }
        return readingStatusRepository.findById(readingStatusId).orElse(null);
    }

    @Override
    public ReadingStatus findReadingStatusbyBook_IdBookAndUsers_Id(UUID idBook, UUID userId) {
        return readingStatusRepository.findByBook_IdBookAndUsers_Id(idBook, userId);
    }

    @Override
    public Page<ReadingStatus> findReadingStatusbyUsers_Id(UUID userId, Pageable pageable) {
        if(userId == null){
            return readingStatusRepository.findAll(pageable);
        }

        return readingStatusRepository.findByUsers_id(userId, pageable);
    }
}

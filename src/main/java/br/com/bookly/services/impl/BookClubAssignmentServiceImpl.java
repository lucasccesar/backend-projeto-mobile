package br.com.bookly.services.impl;

import br.com.bookly.entities.BookClubAssignment;
import br.com.bookly.exceptions.BadRequestException;
import br.com.bookly.exceptions.ExistentBookClubAssignmentException;
import br.com.bookly.exceptions.InexistentBookClubAssignmentException;
import br.com.bookly.exceptions.invalidDateException;
import br.com.bookly.repositories.BookClubAssignmentRepository;
import br.com.bookly.services.BookClubAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookClubAssignmentServiceImpl implements BookClubAssignmentService {

    @Autowired
    BookClubAssignmentRepository bookClubAssignmentRepository;


    @Override
    public BookClubAssignment createBookClubAssignment(BookClubAssignment bookClubAssignment) {

        if (bookClubAssignment.getStartDate() == null){
            throw new BadRequestException("Error: Start date is required");
        }

        if(bookClubAssignment.getFinishDate() == null){
            throw new BadRequestException("Error: Finish date is required");
        }

        //Não permite datas igual (inicio/fim)
        if (bookClubAssignment.getStartDate().isEqual(bookClubAssignment.getFinishDate())){
            throw new invalidDateException("Error: Start and finish dates cannot be the same");
        }

        //Data final menor do que inicial
        if (bookClubAssignment.getStartDate().isAfter(bookClubAssignment.getFinishDate())){
            throw new invalidDateException("Error: Start date cannot be after finish date");
        }

        boolean exists = bookClubAssignmentRepository.existsByBook_IdBookAndBookClub_IdBookClubAndStartDateAndFinishDate(bookClubAssignment.getBook().getIdBook(), bookClubAssignment.getBookClub().getIdBookClub(),
                bookClubAssignment.getStartDate(),
                bookClubAssignment.getFinishDate()
        );

        if (exists) {
            throw new ExistentBookClubAssignmentException("Error: This book is already assigned to the same club with the same dates!");
        }

        return bookClubAssignmentRepository.save(bookClubAssignment);
    }

    @Override
    public boolean deleteById(UUID id) {

        if(bookClubAssignmentRepository.existsById(id) ==  false){
            throw new InexistentBookClubAssignmentException("Error: This BookClub Assignment does not exist");
        }

        bookClubAssignmentRepository.deleteById(id);
        return true;
    }

    @Override
    public BookClubAssignment updateBookClubAssignment(UUID id, BookClubAssignment bookClubAssignment) {

        BookClubAssignment exists = bookClubAssignmentRepository.findById(id).orElse(null);

        if(exists == null){
            throw new InexistentBookClubAssignmentException("Error: This BookClub Assignment does not exist");
        }

        if (bookClubAssignment.getStartDate() == null){
            throw new BadRequestException("Error: Start date is required");
        }

        if(bookClubAssignment.getFinishDate() == null){
            throw new BadRequestException("Error: Finish date is required");
        }

        if (bookClubAssignment.getStartDate().isEqual(bookClubAssignment.getFinishDate())){
            throw new invalidDateException("Error: Start and finish dates cannot be the same");
        }

        if (bookClubAssignment.getStartDate().isAfter(bookClubAssignment.getFinishDate())){
            throw new invalidDateException("Error: Start date cannot be after finish date");
        }

        exists.setFinishDate(bookClubAssignment.getFinishDate());
        exists.setStartDate(bookClubAssignment.getStartDate());

        return bookClubAssignmentRepository.save(exists);
    }

    @Override
    public BookClubAssignment findBookClubAssignmentById(UUID id) {
        return bookClubAssignmentRepository.findById(id).
                orElseThrow(() -> new InexistentBookClubAssignmentException("Error: Book Club Assignment Not Found with this id"));
    }

    @Override
    public Page<BookClubAssignment> findAllBookClubsAssignment(Pageable pageable) {
        return bookClubAssignmentRepository.findAll(pageable);
    }

    @Override
    public Page<BookClubAssignment> findByBookId(UUID bookId, Pageable pageable) {
        Page<BookClubAssignment> assignments = bookClubAssignmentRepository.findByBook_IdBook(bookId, pageable);
        if (assignments.isEmpty()){
            throw new InexistentBookClubAssignmentException("Error: No assignments found for this book");
        }
        return assignments;
    }

    @Override
    public Page<BookClubAssignment> findByBookClubId(UUID bookClubId, Pageable pageable) {
        Page<BookClubAssignment> assignments = bookClubAssignmentRepository.findByBookClub_IdBookClub(bookClubId, pageable);
        if (assignments.isEmpty()){
            throw new InexistentBookClubAssignmentException("Error: No assignments found for this BookClub");
        }
        return assignments;
    }
}

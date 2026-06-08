package br.com.bookly.services;


import br.com.bookly.entities.BookClubAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookClubAssignmentService {
    public BookClubAssignment createBookClubAssignment(BookClubAssignment bookClubAssignment);
    public boolean deleteById(UUID id);
    public BookClubAssignment updateBookClubAssignment(UUID id, BookClubAssignment bookClubAssignment);
    public BookClubAssignment findBookClubAssignmentById(UUID id);
    public Page<BookClubAssignment> findAllBookClubsAssignment(Pageable pageable);
    Page<BookClubAssignment> findByBookId(UUID bookId, Pageable pageable);
    Page<BookClubAssignment> findByBookClubId(UUID bookClubId, Pageable pageable);
}

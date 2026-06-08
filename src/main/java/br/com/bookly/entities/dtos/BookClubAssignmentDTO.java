package br.com.bookly.entities.dtos;

import br.com.bookly.entities.BookClubAssignment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class BookClubAssignmentDTO {

    private UUID idBookClubAssignment;
    private UUID bookId;
    private UUID bookClubId;
    private LocalDate startDate;
    private LocalDate finishDate;

    public BookClubAssignmentDTO(UUID idBookClubAssignment, UUID bookId, UUID bookClubId, LocalDate startDate, LocalDate finishDate) {
        this.idBookClubAssignment = idBookClubAssignment;
        this.bookId = bookId;
        this.bookClubId = bookClubId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public BookClubAssignmentDTO(BookClubAssignment bookClubAssignment) {
        this.idBookClubAssignment = bookClubAssignment.getIdBookClubAssignment();
        this.bookId = bookClubAssignment.getBook().getIdBook();
        this.bookClubId = bookClubAssignment.getBookClub().getIdBookClub();
        this.startDate = bookClubAssignment.getStartDate();
        this.finishDate = bookClubAssignment.getFinishDate();
    }
}

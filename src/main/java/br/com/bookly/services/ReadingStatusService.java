package br.com.bookly.services;

import br.com.bookly.entities.ReadingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReadingStatusService {

    public ReadingStatus createReadingStatus(ReadingStatus readingStatus);
    public ReadingStatus updateReadingStatus(UUID uuid,  ReadingStatus readingStatus);
    boolean deleteReadingStatus(UUID readingStatusId);
    public ReadingStatus findReadingStatus(UUID readingStatusId);
    public ReadingStatus findReadingStatusbyBook_IdBookAndUsers_Id(UUID idBook, UUID id);
    public Page<ReadingStatus> findReadingStatusbyUsers_Id(UUID userId, Pageable pageable);
}

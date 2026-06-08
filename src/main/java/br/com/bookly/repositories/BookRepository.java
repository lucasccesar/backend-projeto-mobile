package br.com.bookly.repositories;

import br.com.bookly.entities.Author;
import br.com.bookly.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findById(UUID id);
    void deleteById(UUID id);
    Page<Book> findByTitleContaining(Pageable pageable, String title);
    //Page<Book> findByAuthor(Pageable pageable, Author author);
    Page<Book> findByGenre(Pageable pageable, String genre);
    @Query("SELECT b FROM Book b WHERE b.availableQuantity > 0")
    Page<Book> findAvailable(Pageable pageable);
    @Query("SELECT b FROM Book b WHERE b.availableQuantity = 0")
    Page<Book> findUnavailable(Pageable pageable);

}

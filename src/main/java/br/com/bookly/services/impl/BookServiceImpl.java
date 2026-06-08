package br.com.bookly.services.impl;

import br.com.bookly.entities.Author;
import br.com.bookly.entities.Book;
import br.com.bookly.exceptions.InexistentAuthorException;
import br.com.bookly.exceptions.InexistentBookException;
import br.com.bookly.exceptions.InvalidBookDataException;
import br.com.bookly.repositories.BookRepository;
import br.com.bookly.services.AuthorService;
import br.com.bookly.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    @Override
    public Book addBook(Book book) {

        if(book.getTitle() == null || book.getTitle().isBlank()) {
            throw new InvalidBookDataException("Error: Empty Book Title");
        }

        if(book.getSynopsis() == null || book.getSynopsis().isBlank()) {
            throw new InvalidBookDataException("Error: Empty Book Synopsis");
        }

        if(book.getGenre() == null || book.getGenre().isBlank()) {
            throw new InvalidBookDataException("Error: Empty Book Genre");
        }

        if(book.getAvailableQuantity() < 0) {
            throw new InvalidBookDataException("Error: Invalid Book Available Quantity (Negative Value)");
        }

        if(book.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidBookDataException("Error: Invalid Book Price (Negative Value)");
        }

        List<Author> processedAuthors = book.getAuthors()
                .stream()
                .map(a -> authorService.getOrCreateAuthorByName(a.getName()))
                .collect(Collectors.toList());

        book.setAuthors(processedAuthors);

        return bookRepository.save(book);

    }

    @Override
    public Book updateBook(UUID id, Book book) {

        if(book == null || id == null){
            throw new InvalidBookDataException("Error: Invalid Book or Book ID");
        }

        Book bookById = bookRepository.findById(id).orElse(null);

        if(bookById == null) {
            throw new InexistentBookException();
        }

        bookById.setTitle(book.getTitle());
        bookById.setSynopsis(book.getSynopsis());
        bookById.setGenre(book.getGenre());
        bookById.setPrice(book.getPrice());

        return bookRepository.save(bookById);

    }

    @Override
    public boolean deleteBook(UUID id) {
        Book bookById = bookRepository.findById(id).orElse(null);

        if(bookById == null) {
            throw new InexistentBookException();
        }

        bookRepository.deleteById(id);

        if(!bookRepository.existsById(id)){
            return true;
        }

        return false;
    }

    @Override
    public Book increaseAvaliableBooks(UUID id, int amount) {

        if(id == null){
            throw new InvalidBookDataException("Error: Null Book ID");
        }

        if(amount <= 0){
            throw new InvalidBookDataException("Error: Invalid Book Amount to Increase (Negative Value)");
        }

        Book bookById = bookRepository.findById(id).orElse(null);

        if(bookById == null) {
            throw new InexistentBookException();
        }

        bookById.setAvailableQuantity(bookById.getAvailableQuantity() + amount);
        bookRepository.save(bookById);

        return bookById;
    }

    @Override
    public Book decreaseAvaliableBooks(UUID id, int amount) {
        if(id == null){
            throw new InvalidBookDataException("Error: Null Book ID");
        }

        Book bookById = bookRepository.findById(id).orElse(null);

        if(bookById == null) {
            throw new InexistentBookException();
        }

        if(bookById.getAvailableQuantity() < amount){
            throw new InvalidBookDataException("Error: Amount to Decrease Greater Than Available Quantity)");
        }

        bookById.setAvailableQuantity(bookById.getAvailableQuantity() - amount);
        bookRepository.save(bookById);

        return bookById;
    }

    @Override
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> getBooksByTitleContaining(Pageable pageable, String title) {
        return bookRepository.findByTitleContaining(pageable, title);
    }

    @Override
    public Page<Book> getBooksByGenre(Pageable pageable, String genre) {
        return bookRepository.findByGenre(pageable, genre);
    }

    public Book getBookById(UUID id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Book> getAvailableBooks(Pageable pageable) {
        return bookRepository.findAvailable(pageable);
    }

    @Override
    public Page<Book> getUnavailableBooks(Pageable pageable) {
        return bookRepository.findUnavailable(pageable);
    }
}

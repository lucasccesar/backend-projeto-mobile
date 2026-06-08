package br.com.bookly.controllers;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.dtos.BookDTO;
import br.com.bookly.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookDTO>> getBooks(Pageable pageable) {
        Page<BookDTO> dtos = bookService.getBooks(pageable).map(BookDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody Book book) {
        Book addedBook = bookService.addBook(book);

        if (addedBook == null) {
            return ResponseEntity.badRequest().build();
        }
        BookDTO bookDTO = new BookDTO(addedBook);
        return ResponseEntity.status(201).body(bookDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable UUID id) {
        Book book = bookService.getBookById(id);
        if (book == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new BookDTO(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable UUID id, @RequestBody Book book) {
        Book updated = bookService.updateBook(id, book);
        if (updated == null) {
            return ResponseEntity.badRequest().build();
        }
        BookDTO bookDTO = new BookDTO(updated);
        return ResponseEntity.status(201).body(bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/increase")
    public ResponseEntity<BookDTO> increaseAvailableBooks(@PathVariable UUID id, @RequestParam int amount) {
        Book updatedBook = bookService.increaseAvaliableBooks(id, amount);

        if (updatedBook == null) {
            return ResponseEntity.badRequest().build();
        }
        BookDTO bookDTO = new BookDTO(updatedBook);
        return ResponseEntity.status(201).body(bookDTO);
    }

    @PutMapping("/{id}/decrease")
    public ResponseEntity<BookDTO> decreaseAvailableBooks(@PathVariable UUID id, @RequestParam int amount) {
        Book updatedBook = bookService.decreaseAvaliableBooks(id, amount);

        if (updatedBook == null) {
            return ResponseEntity.badRequest().build();
        }
        BookDTO bookDTO = new BookDTO(updatedBook);
        return ResponseEntity.status(201).body(bookDTO);
    }

    @GetMapping(params = "title")
    public ResponseEntity<Page<BookDTO>> getBooksByTitle(Pageable pageable, @RequestParam String title) {
        Page<BookDTO> dtos = bookService.getBooksByTitleContaining(pageable, title).map(BookDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(params = "genre")
    public ResponseEntity<Page<BookDTO>> getBooksByGenre(Pageable pageable, @RequestParam String genre) {
        Page<BookDTO> dtos = bookService.getBooksByGenre(pageable, genre).map(BookDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/availables")
    public ResponseEntity<Page<BookDTO>> getAvailableBooks(Pageable pageable) {
        Page<BookDTO> dtos = bookService.getAvailableBooks(pageable).map(BookDTO::new);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/unavailables")
    public ResponseEntity<Page<BookDTO>> getUnavailableBooks(Pageable pageable) {
        Page<BookDTO> dtos = bookService.getUnavailableBooks(pageable).map(BookDTO::new);
        return ResponseEntity.ok(dtos);
    }
}

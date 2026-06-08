package br.com.bookly.services.impl;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.Purchase;
import br.com.bookly.entities.PurchaseBook;
import br.com.bookly.entities.Users;
import br.com.bookly.entities.dtos.PurchaseBookDTO;
import br.com.bookly.entities.dtos.PurchaseDTO;
import br.com.bookly.entities.dtos.PurchaseResponseDto;
import br.com.bookly.exceptions.InexistentBookException;
import br.com.bookly.exceptions.InexistentIdUserException;
import br.com.bookly.exceptions.InexistentPurchaseException;
import br.com.bookly.repositories.PurchaseRepository;
import br.com.bookly.services.PurchaseBookService;
import br.com.bookly.services.PurchaseService;
import br.com.bookly.services.UsersService;
import br.com.bookly.services.BookService;
import br.com.bookly.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UsersService  usersService;

    @Autowired
    PurchaseBookService purchaseBookService;

    @Autowired
    BookService bookService;


    @Override
    public Purchase createPurchase(PurchaseDTO purchaseDTO) {
        Users user = usersService.getUsersRepository()
                .findById(purchaseDTO.getIdUser()).orElse(null);
        if (user == null) return null;

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setTotalValuation(purchaseDTO.getTotalValuation());

        Purchase savedPurchase = purchaseRepository.save(purchase);

        for (PurchaseBookDTO bookDTO : purchaseDTO.getBooks()) {
            Book book = bookService.getBookRepository().findById(bookDTO.getIdBook()).orElse(null);
            if (book == null)
                continue;

            PurchaseBook purchaseBook = new PurchaseBook();
            purchaseBook.setPurchase(savedPurchase);
            purchaseBook.setBook(book);
            purchaseBook.setQuantity(bookDTO.getQuantity());
            purchaseBook.setUnitPrice(bookDTO.getUnitPrice());

            purchaseBookService.getPurchaseBookRepository().save(purchaseBook);

            savedPurchase.getPurchaseBooks().add(purchaseBook);
        }

        return purchaseRepository.save(savedPurchase);
    }


    @Override
    public boolean deletePurchase(UUID id) {
        if(purchaseRepository.existsById(id) == false){
            throw new InexistentPurchaseException();
        }

        purchaseRepository.deleteById(id);
        return true;
    }


    @Override
    public Purchase updatePurchase(UUID id, PurchaseDTO purchase) {
        Purchase exists = purchaseRepository.findById(id).orElse(null);
        if (exists == null)
            throw new InexistentPurchaseException("Error: Purchase with this id doesn't exist");

        if(purchase.getIdUser() == null){
            throw new InexistentIdUserException("Error: User ID must not be null");
        }

        Users user = usersService.getUsersRepository()
                .findById(purchase.getIdUser()).orElse(null);
        if (user == null)
            throw new InexistentIdUserException("Error: User with this ID not found");

        exists.setUser(user);
        exists.getPurchaseBooks().clear();

        for (PurchaseBookDTO pb : purchase.getBooks()) {
            PurchaseBook persistedpb;

            if (pb.getIdBook() != null && purchaseBookService.
                    getPurchaseBookRepository().
                    existsById(pb.getIdBook()))
            {
                persistedpb = purchaseBookService.
                        getPurchaseBookRepository().
                        findById(pb.getIdBook()).get();
            } else {
                persistedpb = new PurchaseBook();
            }

            persistedpb.setPurchase(exists);
            Book book = bookService.getBookRepository().
                    findById(pb.getIdBook()).orElse(null);
            if (book == null)
                throw new InexistentBookException("Error: Book with this ID not found");

            persistedpb.setBook(book);

            persistedpb.setQuantity(pb.getQuantity());
            persistedpb.setUnitPrice(pb.getUnitPrice());

            exists.getPurchaseBooks().add(persistedpb);
        }

        return purchaseRepository.save(exists);
    }

    @Override
    public Purchase findPurchaseById(UUID id) {
        if(id ==  null)
            throw new BadRequestException("Error: Purchase ID must not be null");
        Purchase exists = purchaseRepository.findById(id).orElse(null);
        if (exists == null)
            throw new InexistentPurchaseException("Error: Purchase with this id doesn't exist");
        return exists;
    }

    @Override
    public Page<PurchaseResponseDto> findPurchaseByUser_id(UUID id, Pageable pageable) {
        if (id == null)
            throw new BadRequestException("Error: User ID must not be null");

        Page <Purchase> pg = purchaseRepository.findByUserId_id(id, pageable);

        if(pg.isEmpty()){
            throw new InexistentIdUserException("Error: User with this ID not found");
        }
        return pg.map(PurchaseResponseDto::new);
    }


    @Override
    public Page<PurchaseResponseDto> findPurchaseByDate_purchaseDate(LocalDate date, Pageable pageable) {
        if(date == null || date.isAfter(LocalDate.now())){
            throw new BadRequestException("Invalid date");
        }
        Page<Purchase> pg = purchaseRepository.findByPurchaseDate(date, pageable);
        return pg.map(PurchaseResponseDto::new);
    }

    @Override
    public Page<PurchaseResponseDto> findAllPurchase(Pageable pageable) {
        Page<Purchase> pg = purchaseRepository.findAll(pageable);
        return pg.map(PurchaseResponseDto::new);
    }

}
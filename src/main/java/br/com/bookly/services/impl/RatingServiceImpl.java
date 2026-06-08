package br.com.bookly.services.impl;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.Rating;
import br.com.bookly.entities.Users;
import br.com.bookly.exceptions.*;
import br.com.bookly.repositories.RatingRepository;
import br.com.bookly.services.RatingService;
import br.com.bookly.services.UsersService;
import br.com.bookly.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    BookService bookService;

    @Override
    public Page<Rating> getAllRatings(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }

    @Override
    public Rating addRating(Rating rating) {

        if (rating == null) {
            throw new BadRequestException("Rating cannot be null.");
        }

        if (rating.getUser() == null || rating.getUser().getId() == null) {
            throw new BadRequestException("User ID is required.");
        }

        if (rating.getBook() == null || rating.getBook().getIdBook() == null) {
            throw new BadRequestException("Book ID is required.");
        }
        if (rating.getRatingValue() < 0) {
            throw new InvalidRatingException("Error: Invalid value rating, the minimum value is 0");
        }

        if(rating.getRatingValue() > 10) {
            throw new InvalidRatingException("Error: Invalid value rating, the maximum value is 10");
        }

        Optional<Rating> existingRating = ratingRepository.findByUserIdAndBook_IdBook(
                rating.getUser().getId(),
                rating.getBook().getIdBook()
        );

        if (existingRating.isPresent()) {
            throw new BadRequestException("User has already rated this book.");
        }


        Optional<Users> optionalUser = usersService.getUsersRepository().findById(rating.getUser().getId());
        Optional<Book> optionalBook = bookService.getBookRepository().findById(rating.getBook().getIdBook());

        if (optionalUser.isEmpty()) {
            throw new InexistentIdUserException("Error: User not found");
        }

        if (optionalBook.isEmpty()) {
            throw new InexistentBookException("Error: Book not found");
        }

        rating.setUser(optionalUser.get());
        rating.setBook(optionalBook.get());
        rating.setRatingDate(LocalDate.now());

        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(UUID id, Rating rating) {
        if(rating == null){
            throw new BadRequestException("Error: Rating must not be null");
        }

        if(id == null){
            throw new BadRequestException("Error: Rating ID must not be null");
        }

        Rating ratingDB = ratingRepository.findById(id).orElse(null);

        if(ratingDB == null){
            throw new InexistentRatingException();
        }

        if (!ratingDB.getBook().getIdBook().equals(rating.getBook().getIdBook())) {
            throw new InvalidRatingException("Error: Book ID did you pass and correct Book ID do not match");
        }

        if (!ratingDB.getUser().getId().equals(rating.getUser().getId())) {
            throw new InvalidRatingException("Error: User ID did you pass and correct User ID do not match");

        }

        ratingDB.setComment(rating.getComment());

        if (rating.getRatingValue() < 0) {
            throw new InvalidRatingException("Error: Invalid value rating, the minimum value is 0");
        }

        if(rating.getRatingValue() > 10) {
            throw new InvalidRatingException("Error: Invalid value rating, the maximum value is 10");
        }

        ratingDB.setRatingValue(rating.getRatingValue());
        ratingDB.setRatingDate(LocalDate.now());

        return ratingRepository.save(ratingDB);
    }

    @Override
    public boolean deleteRating(UUID id) {
        if(!ratingRepository.existsById(id)){
            throw new InexistentRatingException("ID reating not exists");

        }

        ratingRepository.deleteById(id);

        if(!ratingRepository.existsById(id)){
            return true;
        }
        return false;
    }

    @Override
    public Page<Rating> getRatingsByBookId(UUID bookId, Pageable pageable) {
        return ratingRepository.findByBook_IdBook(bookId, pageable);
    }
    @Override
    public double getAverageRatingByBookId(UUID bookId) {
        double avg = 0;
        List<Rating> ratings = ratingRepository.findByBook_IdBook(bookId);
        if(bookId == null){
            throw new BadRequestException("Error: Book ID must not be null");
        }

        if(ratings == null){
            throw new BadRequestException("Error: Ratings must not be null");
        }

        for(Rating rating : ratings){
            avg += rating.getRatingValue();
        }
        return avg/ratings.size();
    }
}

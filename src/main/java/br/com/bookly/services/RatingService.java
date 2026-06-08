package br.com.bookly.services;

import br.com.bookly.entities.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;


public interface RatingService {

    Page<Rating> getAllRatings(Pageable pageable);
    Rating addRating(Rating rating);
    Rating updateRating(UUID id, Rating rating);
    boolean deleteRating(UUID id);
    Page<Rating> getRatingsByBookId(UUID bookId, Pageable pageable);
    double getAverageRatingByBookId(UUID bookId);

}

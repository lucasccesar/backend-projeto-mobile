package br.com.bookly.controllers;

import br.com.bookly.entities.Book;
import br.com.bookly.entities.Rating;
import br.com.bookly.entities.Users;
import br.com.bookly.entities.dtos.RatingDTO;
import br.com.bookly.entities.dtos.RatingResponseDto;
import br.com.bookly.entities.dtos.UsersDTO;
import br.com.bookly.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping
    public ResponseEntity<Page<RatingResponseDto>> getRatings(Pageable pageable) {
        Page<RatingResponseDto> ratings = ratingService.getAllRatings(pageable).map(RatingResponseDto::new);
        return ResponseEntity.ok().body(ratings);
    }

    @PostMapping
    public ResponseEntity<RatingResponseDto> addRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.addRating(rating);
        RatingResponseDto ratingDTO = new RatingResponseDto(savedRating);
        return ResponseEntity.ok().body(ratingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingResponseDto> updateRating(@PathVariable UUID id, @RequestBody Rating rating) {
        Rating updated = ratingService.updateRating(id, rating);
        RatingResponseDto ratingDTO = new RatingResponseDto(updated);
        return ResponseEntity.ok(ratingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable UUID id) {
        boolean deleted = ratingService.deleteRating(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/all/{id}")
    public ResponseEntity<Page<RatingResponseDto>> getRatingsByBook(@PathVariable UUID id, Pageable pageable) {
        Page<RatingResponseDto> ratings = ratingService.getRatingsByBookId(id, pageable).map(RatingResponseDto::new);
        return ResponseEntity.ok().body(ratings);
    }

    @GetMapping("/average/{id}")
    public ResponseEntity<Double> getAverageRatingByBookId(@PathVariable UUID id) {
        Double avg = ratingService.getAverageRatingByBookId(id);
        return ResponseEntity.ok().body(avg);
    }
}

package junsung.cinema.moviePacakage.controller;

import junsung.cinema.moviePacakage.dto.ReviewRequestDto;
import junsung.cinema.moviePacakage.dto.ReviewResponseDto;
import junsung.cinema.moviePacakage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies/{movieId}/reviews")

public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> createReview(@PathVariable Long movieId,
                                             @RequestBody ReviewRequestDto dto) {
        reviewService.addReview(movieId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getReviews(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovieId(movieId));
    }
}

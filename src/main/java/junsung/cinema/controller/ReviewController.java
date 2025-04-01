package junsung.cinema.controller;

import junsung.cinema.dto.ReviewRequestDto;
import junsung.cinema.dto.ReviewResponseDto;
import junsung.cinema.service.ReviewService;
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

package junsung.cinema.moviePacakage.service;

import junsung.cinema.moviePacakage.dto.ReviewRequestDto;
import junsung.cinema.moviePacakage.dto.ReviewResponseDto;
import junsung.cinema.moviePacakage.entity.Review;
import junsung.cinema.moviePacakage.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void addReview(Long movieId, ReviewRequestDto dto) {
        Review review = new Review();
        review.setMovieId(movieId);
        review.setContent(dto.getContent());
        review.setWriter(dto.getWriter());
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);
    }

    public List<ReviewResponseDto> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findByMovieIdOrderByCreatedAtDesc(movieId)
                .stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }
}

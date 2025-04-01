package junsung.cinema.service;

import junsung.cinema.dto.ReviewRequestDto;
import junsung.cinema.dto.ReviewResponseDto;
import junsung.cinema.entity.Review;
import junsung.cinema.repository.ReviewRepository;
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

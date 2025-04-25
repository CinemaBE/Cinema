package junsung.cinema.moviePacakage.repository;

import junsung.cinema.moviePacakage.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieIdOrderByCreatedAtDesc(Long movieId);
}

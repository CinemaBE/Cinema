package junsung.cinema.project.dto;

import junsung.cinema.project.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private String content;
    private String writer;
    private LocalDateTime createdAt;

    public ReviewResponseDto(Review review) {
        this.content = review.getContent();
        this.writer = review.getWriter();
        this.createdAt = review.getCreatedAt();
    }
}

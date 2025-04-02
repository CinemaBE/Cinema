package junsung.cinema.moviePacakage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieListResponse {
    private int page;
    private List<MovieDto> results;
}

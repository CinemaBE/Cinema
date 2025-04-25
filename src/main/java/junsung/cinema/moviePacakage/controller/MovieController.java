package junsung.cinema.moviePacakage.controller;

import junsung.cinema.moviePacakage.dto.MovieDto;
import junsung.cinema.moviePacakage.dto.MovieListResponse;
import junsung.cinema.moviePacakage.service.TmdbService;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/movies")
//public class MovieController {
//
//    private final TmdbService tmdbService;
//
//    public MovieController(TmdbService tmdbService) {
//        this.tmdbService = tmdbService;
//    }
//
//    // 현재 상영작 목록
//    @GetMapping("/now-playing")
//    public MovieListResponse getNowPlayingMovies() {
//        return tmdbService.getNowPlayingMovies();
//    }
//
//    // 영화 제목 검색
//    @GetMapping("/search")
//    public MovieListResponse searchMovies(@RequestParam("query") String query) {
//        return tmdbService.searchMoviesByTitle(query);
//    }
//
//
//}

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final TmdbService tmdbService;

    public MovieController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    // 현재 상영작 목록
    @GetMapping("/now-playing")
    public MovieListResponse getNowPlayingMovies() {
        return tmdbService.getNowPlayingMovies();
    }

    // 영화 제목 검색
    @GetMapping("/search")
    public MovieListResponse searchMovies(@RequestParam("query") String query) {
        return tmdbService.searchMoviesByTitle(query);
    }

    // 영화 상세 정보
    @GetMapping("/detail/{id}")
    public MovieDto getMovieDetail(@PathVariable("id") Long id) {
        return tmdbService.getMovieDetailWithCredits(id);
    }
}

package com.cinema.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class MovieController {
    private final String API_KEY = "f0a397f188e2de2ecf3a169bb7249343";
    private final String BASE_URL = "https://api.themoviedb.org/3";
    private final RestTemplate restTemplate;

    public MovieController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*
      영화 검색 API
      예시 : 미션 임파서블
     */
    @GetMapping("/view")
    public ResponseEntity<List<MovieResponseDTO>> searchMovies(@RequestParam String query) {

        try {

            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            String url = String.format(
                    "https://api.themoviedb.org/3/search/movie?query=%s&api_key=f0a397f188e2de2ecf3a169bb7249343&language=ko-KR&region=KR",
                    encodedQuery
            );

            // API 요청 및 응답 처리
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

            // 검색 결과가 없을 경우 빈 리스트 반환
            if (results == null || results.isEmpty()) {
                return ResponseEntity.ok(List.of());
            }
        // release_data   Java : releaseData
            // DTO 변환 (가독성 개선)
        List<MovieResponseDTO> movies = results.stream()
                .map(movie -> new MovieResponseDTO(
                        (String) movie.getOrDefault("title", "제목 없음"),
                        (String) movie.getOrDefault("release_date", "개봉 정보 없음"),
                        (String) movie.getOrDefault("overview", "설명 없음"),
                        formatPosterPath((String) movie.get("poster_path")),
                        ((Number) movie.getOrDefault("vote_average", 0)).doubleValue()
                ))
                .collect(Collectors.toList());

            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    // 영화 상세 정보 조회 API
    @GetMapping("/{movieId}")
    public ResponseEntity<String> getMovieDetails(@PathVariable String movieId) {
        String url = String.format("%s/movie/%s?api_key=%s&language=ko-KR", BASE_URL, movieId, API_KEY);

        try {
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid movie ID: " + e.getMessage());
        }
    }

    // 한글이 포함된 검색어를 URL 인코딩

    private String encodeQuery(String query) {
        try {
            return URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            return query;
        }
    }


//    포스터 이미지 경로 반환
    private String formatPosterPath(String posterPath) {
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return (posterPath != null && !posterPath.isEmpty()) ? baseUrl + posterPath : "https://via.placeholder.com/200";
    }
}

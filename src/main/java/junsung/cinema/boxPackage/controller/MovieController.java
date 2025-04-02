package junsung.cinema.boxPackage.controller;

import junsung.cinema.boxPackage.service.BoxOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final BoxOfficeService boxOfficeService;

    @GetMapping("/boxoffice")
    public ResponseEntity<String> boxOffice() {
        return ResponseEntity.ok(boxOfficeService.getTodayBoxOfficeList()) ;
    }
}

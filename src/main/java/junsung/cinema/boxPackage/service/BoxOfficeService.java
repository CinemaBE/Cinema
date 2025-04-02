package junsung.cinema.boxPackage.service;

import junsung.cinema.boxPackage.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class BoxOfficeService {

    private Movie movie;

    private static final String API_KEY = "a6f2e636cb6d99286a975200445feeb3";
    private static final String BASE_URL =  "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
    private final RestTemplate restTemplate = new RestTemplate();

    public String getTodayBoxOfficeList() {
        String yesterday = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String requestUrl = BASE_URL +
                "?key=" + API_KEY +
                "&targetDt=" + yesterday +
                "&itemPerPage=10" ;

        return restTemplate.getForObject(requestUrl, String.class);
    }

    

}

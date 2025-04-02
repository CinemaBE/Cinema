package junsung.cinema.AuthPackage.controller;


import junsung.cinema.AuthPackage.dto.UsersDTO;
import junsung.cinema.AuthPackage.dto.LoginRequest;
import junsung.cinema.AuthPackage.entity.Users;
import junsung.cinema.AuthPackage.security.JwtUtil;
import junsung.cinema.AuthPackage.service.AuthService;
import junsung.cinema.AuthPackage.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")

public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final UserDetailService userDetailService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest ) {
       // Users users = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        UserDetails users = userDetailService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(loginRequest.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        authService.authority(users.getUsername());
        return ResponseEntity.ok()
                .headers(headers)
                .body("유저 ID : "+ users.getUsername());
    }//헤더에 토큰 담기 추가

    @PostMapping("/signup")
    public ResponseEntity<UsersDTO> signup(@RequestBody Users user) {
        Users users = authService.signUp(user.getUsername(), user.getPassword(), user.getPass(),
                user.getEmail(), user.getPhone(), user.getName(), user.getBirth());

        return ResponseEntity.ok(new UsersDTO(users));
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(Map.of("username", user.getUsername()));
    }




}
//find > load 로 수정
// token ==null 메서드 제가 >> load에서 찾아줌
//회원 등급 정하기 메서드 , 로그인 메서드 분리
/*{
  "username": "exampleUser",
  "password": "encryptedPassword",
  "pass" : "encryptedPassword",
  "name": "홍길동",
  "phone": "010-1234-5678",
  "email": "example@example.com",
  "birth": "2000-01-01",
  "count": 0
}
*/

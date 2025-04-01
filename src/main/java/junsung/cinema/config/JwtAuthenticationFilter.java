package junsung.cinema.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import junsung.cinema.entity.Grade;
import junsung.cinema.entity.Users;
import junsung.cinema.repository.UserRepository;
import junsung.cinema.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");//공개 키
        if(token != null && token.startsWith("Bearer ")) {
           token = token.substring(7);
          String username = jwtUtil.validateToken(token);//토큰 검증

        Users user = userRepository.findByUsername(username);
        int visit = user.getCount();//방문횟수
            System.out.println("방문횟수" +visit);
        switch (visit) {
            case 5:
                user.setGrade(Grade.GOLD);

                break;
            case 10:
                user.setGrade(Grade.PLATINUM);
                break;
            case 20:
                user.setGrade(Grade.DIAMOND);
                break;
            default:
                break;

        }
            userRepository.save(user);}

       filterChain.doFilter(request,response);
    }
}

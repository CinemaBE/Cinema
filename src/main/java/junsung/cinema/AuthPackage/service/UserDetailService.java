package junsung.cinema.AuthPackage.service;

import junsung.cinema.AuthPackage.entity.Users;
import junsung.cinema.AuthPackage.repository.UserRepository;
import junsung.cinema.AuthPackage.security.CustomUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 : "+username);

        }

        return new CustomUserDetail(user);
    }
}

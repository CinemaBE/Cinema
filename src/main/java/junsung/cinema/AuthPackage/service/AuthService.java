package junsung.cinema.AuthPackage.service;

import junsung.cinema.AuthPackage.entity.Grade;
import junsung.cinema.AuthPackage.entity.Users;
import junsung.cinema.AuthPackage.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;


    public Users signUp(String username, String password1, String password2 , String email , String phone, String name, String birth) {
        if (userRepository.findByEmail(email) != null)
        {throw new IllegalArgumentException("이미 존재하는 email입니다");}
        if(!password1.equals(password2)) {            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Users user = new Users();
        user.setUsername(username);
            user.setPassword(bcryptPasswordEncoder.encode(password1));
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setBirth(birth);
        user.setCount(0);
        user.setGrade(Grade.BRONZE);

        userRepository.save(user);
        return user;
    }

    public void authority(String username) {
        Users users = userRepository.findByUsername(username );

            int visit = users.getCount();//방문횟수
            System.out.println("방문횟수" +visit);
            switch (visit) {
                case 5:
                    users.setGrade(Grade.GOLD);

                    break;
                case 10:
                    users.setGrade(Grade.PLATINUM);
                    break;
                case 20:
                    users.setGrade(Grade.DIAMOND);
                    break;
                default:
                    break;

            }
            userRepository.save(users);

        }
}

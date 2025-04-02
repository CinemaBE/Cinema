package junsung.cinema.AuthPackage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Users {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message ="아이디를 입력해주세요.")
    @Size(min =5, max= 20 ,message="아이디는 4~20자 이내이어야 합니다.")
    private String username;

    @NotBlank(message ="비밀번호를 입력해주세요.")
    @Size(min = 8 ,message="비밀번호는 8자 이상이어야 합니다.")
    private String password;
    private String pass;

    @Email
    private String email;
    private String phone;
    private String birth;
    private String name;

    private int count;

    @Enumerated(EnumType.STRING)
    private Grade grade;

}
package com.plusweek.domain.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$" , message = "영어 대소문자와 숫자를 이용하여 3~20자 사이로 만드세요")
    String nickname;

    @Size(min = 4, message = "4자 이상으로 만들어주세요")
    String password;

    String checkPassword;

//    @Pattern(regexp = "^([a-z0-9]+)@([\\da-z\\.-]+)\\.([a-z\\.]{1,50})$", message = "이메일 형식으로 작성해주세요")
//    private String email;

    String  role;

}

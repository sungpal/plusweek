package com.plusweek.domain.user.service;

import com.plusweek.domain.user.dto.UserSignUpRequestDto;
import com.plusweek.domain.user.entity.User;
import com.plusweek.domain.user.entity.UserRoleEnum;
import com.plusweek.domain.user.repository.UserRepository;
import com.plusweek.exception.CustomException;
import com.plusweek.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signupUser(UserSignUpRequestDto userSignUpRequestDto) {
        if(userSignUpRequestDto.getPassword().contains(userSignUpRequestDto.getNickname())){
            throw new CustomException(ExceptionCode.CONFLICT_PASSWORD_INCLUDED_IN_NICKNAME);
        }

        if (!userSignUpRequestDto.getPassword().equals(userSignUpRequestDto.getCheckPassword())) {
            throw new CustomException(ExceptionCode.CONFLICT_PASSWORD_NOT_CORRECT);
        }
        if (userRepository.existsByNickname(userSignUpRequestDto.getNickname())) {
            throw new CustomException(ExceptionCode.CONFLICT_NICKNAME_IN_USE);
        }
        // BCryptPasswordEncoder를 이용하여 단방향 알고리즘을 적용한 password 생성
        String password = passwordEncoder.encode(userSignUpRequestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;

        if(userSignUpRequestDto.getRole().equals("admin")) {
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(userSignUpRequestDto.getNickname(), password, role);
        this.userRepository.save(user);
    }

    public void checkNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ExceptionCode.CONFLICT_NICKNAME_IN_USE);
        }
    }
}

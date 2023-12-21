package com.plusweek.domain.user.controller;

import com.plusweek.domain.EmailService;
import com.plusweek.domain.user.dto.UserSignUpRequestDto;
import com.plusweek.domain.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@Valid @RequestBody UserSignUpRequestDto userSignUpRequestDto) throws MessagingException {
        String password = userSignUpRequestDto.getPassword();
        String nickname = userSignUpRequestDto.getNickname();


//        userService.signupUser(userSignUpRequestDto);
//        emailService.sendEmail(userSignUpRequestDto);

        return ResponseEntity.status(201).body("회원가입 완료");
    }

    @GetMapping("/signup/{nickname}")
    public ResponseEntity<String> checkNickname(@RequestParam(name = "nickname") String nickname) {
        userService.checkNickname(nickname);
        return ResponseEntity.status(201).body("중복되지 않은 닉네임입니다");
    }
}

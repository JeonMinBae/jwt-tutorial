package com.jwt.icraft.auth.controller;

import com.jwt.icraft.auth.dto.SignInDto;
import com.jwt.icraft.auth.dto.SignUpDto;
import com.jwt.icraft.auth.service.AuthService;
import com.jwt.icraft.jwt.TokenProvider;
import com.jwt.icraft.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto) {

        String token = authService.signIn(signInDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(TokenProvider.AUTHORIZATION_HEADER_NAME, TokenProvider.TOKEN_PREFIX + token);

        return ResponseEntity.ok()
            .headers(httpHeaders)
            .body("issue");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) throws URISyntaxException {

        UserEntity userEntity = authService.signUp(signUpDto);

        return ResponseEntity.created(new URI("/sign-in"))
            .body(userEntity);
    }

}

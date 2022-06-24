package com.jwt.icraft.auth.service;

import com.jwt.icraft.auth.dto.SignInDto;
import com.jwt.icraft.auth.dto.SignUpDto;
import com.jwt.icraft.jwt.TokenProvider;
import com.jwt.icraft.user.entity.IUserRepository;
import com.jwt.icraft.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;


    @Transactional
    public String signIn(SignInDto signInDto){

        Authentication authentication =
            authenticationManagerBuilder.getObject().authenticate(signInDto.createUsernamePasswordAuthenticationToken());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.issueToken(authentication);
    }

    @Transactional
    public UserEntity signUp(SignUpDto signUpDto){

        return iUserRepository.save(signUpDto.createUserEntity(passwordEncoder.encode(signUpDto.getUserPassword())));
    }




}

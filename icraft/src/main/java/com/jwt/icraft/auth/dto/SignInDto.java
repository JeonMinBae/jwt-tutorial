package com.jwt.icraft.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {

    private String userId;
    private String userPassword;

    public UsernamePasswordAuthenticationToken createUsernamePasswordAuthenticationToken(){
        return new UsernamePasswordAuthenticationToken(userId, userPassword);
    }
}

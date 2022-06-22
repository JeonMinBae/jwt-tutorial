package com.jwt.icraft.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private final Long tokenExpiredMilliseconds = 60 * 1000L;

    //토큰생성
    public String issueToken(Authentication authentication){

        Date iat = new Date();
        String roles = authentication.getAuthorities()
            .stream().map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        return Jwts.builder()
            .setIssuer("Jwt tutorial")
            .setSubject("Test Jwt")
            .setAudience(authentication.getName())
            .setIssuedAt(iat)
            .setExpiration(new Date(iat.getTime() + tokenExpiredMilliseconds))
            .claim("roles", roles)
            .signWith(secretKey)
            .compact();
    }

    //Authentication 추출
    public Authentication getAuthenticationFrom(String token){
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get("roles").toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());


        return new UsernamePasswordAuthenticationToken(claims.getAudience(), token, authorities);

    }

    //토큰검증
    public static boolean validateToken(String token) throws ExpiredJwtException{
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return true;
    }

}

package com.jwt.icraft.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String token = extractTokenFrom(request);

        System.out.println("token: [" + token + "]");

        try {
            if(!token.isBlank() && TokenProvider.validateToken(token)){
                Authentication authentication = tokenProvider.getAuthenticationFrom(token);

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }catch (ExpiredJwtException e){
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            responseBody.put("error", "Unauthorized");
            responseBody.put("message", e.getMessage());
            responseBody.put("path", request.getServletPath());

            ObjectMapper om = new ObjectMapper();
            om.writeValue(response.getOutputStream(), responseBody);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFrom(HttpServletRequest request){
        String bearerToken = request.getHeader(TokenProvider.AUTHORIZATION_HEADER_NAME);

        System.out.println("bearerToken: [" + bearerToken + "]");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TokenProvider.TOKEN_PREFIX)){
            return bearerToken.substring(TokenProvider.TOKEN_PREFIX.length());
        }

        return "";
    }
}

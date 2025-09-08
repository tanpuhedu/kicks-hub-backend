package com.tanpuh.kickshubservice.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class AuthenticationUtils {
    public static String extractUserFromJwt() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        JwtAuthenticationToken contextHolder = (JwtAuthenticationToken) authentication;
        return contextHolder.getToken().getSubject();
    }
}

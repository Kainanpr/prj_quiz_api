package com.prj.quiz.security;

import com.prj.quiz.service.CustomsUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final JWTUtil jwtUtil;
    private final CustomsUserDetailsService customsUserDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, CustomsUserDetailsService customsUserDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.customsUserDetailsService = customsUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String headerToken = request.getHeader("Authorization");

        if (headerToken != null && headerToken.startsWith("Bearer ")) {
            final UsernamePasswordAuthenticationToken auth = getAuthentication(request, headerToken.substring(7));
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
        if (jwtUtil.tokenIsValid(token)) {
            final String email = jwtUtil.getUsername(token);
            final UserDetails user = customsUserDetailsService.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }
}

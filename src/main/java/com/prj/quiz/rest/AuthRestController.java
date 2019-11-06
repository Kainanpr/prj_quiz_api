package com.prj.quiz.rest;

import com.prj.quiz.model.User;
import com.prj.quiz.security.JWTUtil;
import com.prj.quiz.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/v1/auth")
public class AuthRestController {
    private final JWTUtil jwtUtil;

    public AuthRestController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/refresh-token", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> refreshToken(HttpServletResponse response) {
        final User user = UserService.authenticated();
        final String token = jwtUtil.generateToken(user.getEmail());
        final String bearerToken = "Bearer " + token;
        response.addHeader("Authorization", bearerToken);
        return ResponseEntity.ok().body(bearerToken);
    }
}


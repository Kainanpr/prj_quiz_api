package com.prj.quiz.config.security;

import com.prj.quiz.security.JWTAuthenticationFilter;
import com.prj.quiz.security.JWTAuthorizationFilter;
import com.prj.quiz.security.JWTUtil;
import com.prj.quiz.service.CustomsUserDetailsService;
import com.prj.quiz.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomsUserDetailsService customsUserDetailsService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    public SecurityConfig(CustomsUserDetailsService customsUserDetailsService, UserService userService, JWTUtil jwtUtil) {
        this.customsUserDetailsService = customsUserDetailsService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public static final String[] PUBLIC_MATCHERS_GET = {
            "/v1/users/is-admin"
    };

    public static final String[] PUBLIC_MATCHERS_POST = {
            "/v1/users"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .anyRequest().authenticated();
        http.addFilter(getJWTAuthenticationFilter());
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, customsUserDetailsService));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customsUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedMethods(List.of("*"));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    public JWTAuthenticationFilter getJWTAuthenticationFilter() throws Exception {
        final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager(), userService, jwtUtil);
        filter.setFilterProcessesUrl("/v1/login");
        return filter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

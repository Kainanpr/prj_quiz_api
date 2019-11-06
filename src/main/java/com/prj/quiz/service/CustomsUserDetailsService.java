package com.prj.quiz.service;

import com.prj.quiz.model.RolesEnum;
import com.prj.quiz.model.User;
import com.prj.quiz.persistence.repository.UserRepository;
import com.prj.quiz.security.CustomsUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomsUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    private CustomsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        final List<RolesEnum> roles = new ArrayList<>();
        roles.add(RolesEnum.USER);
        if (user.isAdmin()) {
            roles.add(RolesEnum.ADMIN);
        }

        return new CustomsUserDetails(user, roles);
    }
}

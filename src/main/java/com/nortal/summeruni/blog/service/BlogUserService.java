package com.nortal.summeruni.blog.service;

import com.nortal.summeruni.blog.entity.BlogUser;
import com.nortal.summeruni.blog.entity.BlogUserDto;
import com.nortal.summeruni.blog.exeption.UsernameTakenException;
import com.nortal.summeruni.blog.repository.BlogUserRepository;
import com.nortal.summeruni.blog.security.BlogUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogUserService implements UserDetailsService {

    @Autowired
    private final BlogUserRepository blogUserRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return blogUserRepository.findAll()
                .stream()
                .filter(blogUser -> username.equals(blogUser.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Couldn't find user with username %s", username)));
    }

    public Long registerUser(BlogUserDto blogUserDto) throws UsernameTakenException {
        try {
            loadUserByUsername(blogUserDto.getUsername());
        } catch (UsernameNotFoundException e) {
            BlogUser blogUser = BlogUser.builder()
                    .username(blogUserDto.getUsername())
                    .password(passwordEncoder.encode(blogUserDto.getPassword()))
                    .email(blogUserDto.getEmail())
                    .role(BlogUserRole.REGULAR_USER.name())
                    .isCredentialsNonExpired(true)
                    .isAccountNonLocked(true)
                    .isAccountNonExpired(true)
                    .isEnabled(true)
                    .build();
            return blogUserRepository.save(blogUser).getId();
        }
        throw new UsernameTakenException("Username is already taken");
    }
}

package com.nortal.summeruni.blog.security;

import com.nortal.summeruni.blog.jwt.JwtTokenVerifier;
import com.nortal.summeruni.blog.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.nortal.summeruni.blog.service.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final BlogUserService blogUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()// To access h2 console
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login", "/api/register").permitAll()
                .antMatchers(HttpMethod.GET, "/blog", "/blog/**").authenticated()
                .antMatchers(HttpMethod.POST, "/blog/{blogPostId}").access("@blogUserSecurity.isCreatedByUser(authentication,#blogPostId)")
                .antMatchers(HttpMethod.PUT, "/blog/{blogPostId}").access("@blogUserSecurity.isCreatedByUser(authentication,#blogPostId)")
                .antMatchers(HttpMethod.DELETE, "/blog/{blogPostId}").access("@blogUserSecurity.isCreatedByUser(authentication,#blogPostId)");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(blogUserService);
        return provider;
    }
}

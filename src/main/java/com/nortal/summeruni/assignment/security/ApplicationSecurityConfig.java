package com.nortal.summeruni.assignment.security;

import com.nortal.summeruni.assignment.service.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_OK;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final BlogUserService blogUserService;

    @Autowired
    private final BlogUserSecurity blogUserSecurity;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: return message instead of login form
        // TODO:
        http
                .csrf().disable()
                .headers().frameOptions().disable()// To access h2 console
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api", "/api/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/{blogPostId}").access("@blogUserSecurity.isCreatedByUser(authentication,#blogPostId)")
                .antMatchers(HttpMethod.PUT, "/api/{blogPostId}").access("@blogUserSecurity.isCreatedByUser(authentication,#blogPostId)")
                .antMatchers(HttpMethod.DELETE, "/api/{blogPostId}").access("@blogUserSecurity.isCreatedByUser(authentication,#blogPostId)")
                .and()
                .formLogin()
                    .loginProcessingUrl("/auth/login")
                    .permitAll()
                    .successHandler((request, response, authentication) -> response.setStatus(SC_OK))
                    .failureHandler((req, resp, ex) -> resp.sendError(SC_FORBIDDEN))
                .and()
                    .rememberMe()
                .and()
                .logout()
                    .logoutUrl("/auth/logout")
                    .logoutSuccessHandler((request, response, authentication) -> response.setStatus(SC_OK))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me");
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

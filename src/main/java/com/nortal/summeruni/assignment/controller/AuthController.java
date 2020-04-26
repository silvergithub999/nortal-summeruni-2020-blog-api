package com.nortal.summeruni.assignment.controller;

import com.nortal.summeruni.assignment.entity.BlogUserDto;
import com.nortal.summeruni.assignment.exeption.UsernameTakenException;
import com.nortal.summeruni.assignment.service.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final BlogUserService blogUserService;

    @PostMapping("register")
    public Long register(@RequestBody BlogUserDto blogUserDto) throws UsernameTakenException {
        return blogUserService.registerUser(blogUserDto);
    }
}

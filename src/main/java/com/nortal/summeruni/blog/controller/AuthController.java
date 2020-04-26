package com.nortal.summeruni.blog.controller;

import com.nortal.summeruni.blog.entity.BlogUserDto;
import com.nortal.summeruni.blog.exeption.UsernameTakenException;
import com.nortal.summeruni.blog.service.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {

    private final BlogUserService blogUserService;

    @PostMapping("register")
    public Long register(@RequestBody BlogUserDto blogUserDto) throws UsernameTakenException {
        return blogUserService.registerUser(blogUserDto);
    }
}

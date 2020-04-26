package com.nortal.summeruni.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogUserDto {

    private String email;

    private  String username;

    private String password;

}

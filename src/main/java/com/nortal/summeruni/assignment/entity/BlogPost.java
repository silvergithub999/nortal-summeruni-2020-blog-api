package com.nortal.summeruni.assignment.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @Lob
    private String content;

    @NotEmpty
    private String timeOfCreation;
}

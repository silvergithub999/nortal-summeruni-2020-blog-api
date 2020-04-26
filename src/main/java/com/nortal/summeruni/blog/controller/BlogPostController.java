package com.nortal.summeruni.blog.controller;

import com.nortal.summeruni.blog.entity.BlogPost;
import com.nortal.summeruni.blog.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    @PostMapping
    public Long createBlogPost(@RequestBody BlogPost blogPost) {
        return blogPostService.createBlogPost(blogPost);
    }

    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GetMapping("/{id}")
    public BlogPost getBlogPost(@PathVariable("id") long id) {
        return blogPostService.getBlogPost(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable("id") long id) {
        blogPostService.deleteBlogPost(id);
    }

    @PutMapping("/{id}")
    public void changeBlogPost(@PathVariable("id") long id, @RequestBody BlogPost blogPost) {
        blogPostService.changeBlogPost(id, blogPost);
    }

}

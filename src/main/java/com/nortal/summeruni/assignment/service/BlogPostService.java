package com.nortal.summeruni.assignment.service;

import com.nortal.summeruni.assignment.entity.BlogPost;
import com.nortal.summeruni.assignment.entity.BlogUser;
import com.nortal.summeruni.assignment.repository.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public Long createBlogPost(BlogPost blogPost) {
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        blogPost.setAuthor(author);
        blogPost.setTimeOfCreation(getCurrentTimeAsISOString());
        return blogPostRepository.save(blogPost).getId();
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getBlogPost(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find the blog post with id: " + id));
    }

    public void deleteBlogPost(Long id) {
        blogPostRepository.deleteById(id);
    }

    public void changeBlogPost(Long id, BlogPost blogPost) {
        BlogPost blogPostOriginal = blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find the blog post to update with id: " + id));

        blogPostOriginal.setTimeOfCreation(getCurrentTimeAsISOString());
        blogPostOriginal.setTitle(blogPost.getTitle());
        blogPostOriginal.setContent(blogPost.getContent());

        blogPostRepository.save(blogPostOriginal);
    }

    private String getCurrentTimeAsISOString() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(new Date());
    }
}

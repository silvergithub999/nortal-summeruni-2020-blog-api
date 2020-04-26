package com.nortal.summeruni.assignment.security;

import com.nortal.summeruni.assignment.entity.BlogPost;
import com.nortal.summeruni.assignment.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("blogUserSecurity")
@RequiredArgsConstructor
public class BlogUserSecurity {

    @Autowired
    private final BlogPostService blogPostService;

    public boolean isCreatedByUser(Authentication authentication, Long postId) {
        // Alternative ways is to block unauthorised users from deleting and changing posts in the Controller
        // Might not be the most efficient approach, since might make more requests to db
        String username = authentication.getName();  // TODO: make sure is correct
        BlogPost blogPost = blogPostService.getBlogPost(postId);
        return blogPost.getAuthor().equals(username);
    }
}

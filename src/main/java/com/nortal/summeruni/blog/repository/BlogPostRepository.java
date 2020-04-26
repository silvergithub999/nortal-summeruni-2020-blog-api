package com.nortal.summeruni.blog.repository;

import com.nortal.summeruni.blog.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}

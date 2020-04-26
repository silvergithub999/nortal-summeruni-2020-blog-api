package com.nortal.summeruni.assignment.repository;

import com.nortal.summeruni.assignment.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}

package com.nortal.summeruni.blog.repository;

import com.nortal.summeruni.blog.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
}

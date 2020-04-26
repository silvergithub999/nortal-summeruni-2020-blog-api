package com.nortal.summeruni.assignment.repository;

import com.nortal.summeruni.assignment.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
}

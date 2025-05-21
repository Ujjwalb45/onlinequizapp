package com.ujjwal.quiz.repository;

import com.ujjwal.quiz.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    public Admin findByUsername(String username);
}

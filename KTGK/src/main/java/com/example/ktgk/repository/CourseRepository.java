package com.example.ktgk.repository;

import com.example.ktgk.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface CourseRepository extends JpaRepository<Course,Long> {

    Page<Course> findByNameContaining(String keyword, Pageable pageable);

}
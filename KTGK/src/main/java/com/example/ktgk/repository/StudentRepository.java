package com.example.ktgk.repository;

import com.example.ktgk.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByUsername(String username);

}
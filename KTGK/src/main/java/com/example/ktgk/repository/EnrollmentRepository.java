package com.example.ktgk.repository;

import com.example.ktgk.model.Enrollment;
import com.example.ktgk.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    List<Enrollment> findByStudent(Student student);

}
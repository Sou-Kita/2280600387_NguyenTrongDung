package com.example.ktgk.controller;

import com.example.ktgk.model.Course;
import com.example.ktgk.model.Enrollment;
import com.example.ktgk.model.Student;
import com.example.ktgk.repository.CourseRepository;
import com.example.ktgk.repository.EnrollmentRepository;
import com.example.ktgk.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    // enroll course
    @GetMapping("/{id}")
    public String enrollCourse(@PathVariable Long id,
                               Authentication authentication) {

        String username = authentication.getName();

        Student student = studentRepository
                .findByUsername(username);

        Course course = courseRepository
                .findById(id)
                .orElse(null);

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDate.now());

        enrollmentRepository.save(enrollment);

        return "redirect:/enroll/mycourses";
    }

    // my courses
    @GetMapping("/mycourses")
    public String myCourses(Model model,
                            Authentication authentication) {

        String username = authentication.getName();

        Student student = studentRepository
                .findByUsername(username);

        List<Enrollment> courses =
                enrollmentRepository.findByStudent(student);

        model.addAttribute("courses", courses);

        return "mycourses";
    }

}
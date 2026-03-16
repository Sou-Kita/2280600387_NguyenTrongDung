package com.example.ktgk.controller;

import com.example.ktgk.model.Course;
import com.example.ktgk.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses")
public class AdminCourseController {

    @Autowired
    private CourseRepository courseRepository;

    // list courses
    @GetMapping
    public String listCourses(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        return "admin-courses";
    }

    // form create
    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("course", new Course());
        return "course-form";
    }

    // save
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course){
        courseRepository.save(course);
        return "redirect:/admin/courses";
    }

    // edit
    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model){
        Course course = courseRepository.findById(id).orElse(null);
        model.addAttribute("course", course);
        return "course-form";
    }

    // delete
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseRepository.deleteById(id);
        return "redirect:/admin/courses";
    }
}
package com.example.ktgk.controller;

import com.example.ktgk.model.Course;
import com.example.ktgk.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Controller
public class HomeController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping({"/","/home"})
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(required = false) String keyword) {

        Page<Course> courses;

        if(keyword != null && !keyword.isEmpty()){

            courses = courseRepository
                    .findByNameContaining(keyword,
                            PageRequest.of(page,5));

        }else{

            courses = courseRepository
                    .findAll(PageRequest.of(page,5));

        }

        model.addAttribute("courses",courses);

        return "home";
    }
}
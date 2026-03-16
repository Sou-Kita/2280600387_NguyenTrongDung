package com.example.ktgk.controller;

import com.example.ktgk.model.Role;
import com.example.ktgk.model.Student;
import com.example.ktgk.repository.RoleRepository;
import com.example.ktgk.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute("student",new Student());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Student student){

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        Role role = roleRepository.findByName("STUDENT");

        student.setRoles(Set.of(role));

        studentRepository.save(student);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
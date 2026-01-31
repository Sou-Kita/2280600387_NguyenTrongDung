package com.example.buoi3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Homecontroller {
    @GetMapping("/home")
    public String Index(){
        return "index";
    }
}
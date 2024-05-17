package com.cool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class CommonController {
    @GetMapping("/")
    public String main() {
        return "index.html";
    }

    @GetMapping("/glasses")
    public String glasses() {
        return "g51.html";
    }
}

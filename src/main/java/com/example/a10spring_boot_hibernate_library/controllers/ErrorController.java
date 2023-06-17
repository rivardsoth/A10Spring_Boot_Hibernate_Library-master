package com.example.a10spring_boot_hibernate_library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ErrorController {

    @GetMapping("/invalid")
    public String handleError(@RequestParam("message") String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return "error-page";
    }
}
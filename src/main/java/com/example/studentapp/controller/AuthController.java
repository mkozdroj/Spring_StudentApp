package com.example.studentapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AuthController {

    @GetMapping("/login")
    public String getLogin() {
        return "auth/login";
    }


    @GetMapping("/register")
    public String getRegister() {
        return "auth/register";
    }
}

package com.project.consultant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HelloResource {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}

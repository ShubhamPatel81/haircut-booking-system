package com.hair.User_Service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String homeController(){
        return "This is the home controller of User-Service for Hair Cutting System";
    }
}

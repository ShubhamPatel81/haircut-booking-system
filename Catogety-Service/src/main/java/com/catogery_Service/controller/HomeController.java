package com.catogery_Service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class  HomeController {

    @GetMapping
    public String homeControllerCatagory(){
        return "This is the Home controller of Category microservice";
    }
}

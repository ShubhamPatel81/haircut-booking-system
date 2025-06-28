package com.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

    @GetMapping
    public String homeControllerPayment(){
        return "This is the Home controller of Payment microservice";
    }
}

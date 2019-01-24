package com.chang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/message")
    public String Message(String message){
        return "message";
    }
}


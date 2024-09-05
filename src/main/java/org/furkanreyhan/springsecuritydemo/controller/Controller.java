package org.furkanreyhan.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping("/hello")
    String hello(){
        return "HELLO WORLD";
    }
    @GetMapping("/hello2")
    String hello2(){
        return "HELLO WORLD 2";
    }
}

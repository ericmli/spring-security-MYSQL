package io.isiflix.authdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello world!";
    }

    @GetMapping("/auth")
    public String auth(){
        return "Hello Spring from Spring Security Authenticated EndPoint";
    }

    @GetMapping("/user")
    public String sayHelloToUser(){
        return "Hello World to User";
    }

    @GetMapping("/admin")
    public String sayHelloToAdmin(){
        return "Hello World to Admin";
    }

    @GetMapping("/master")
    public String sayHelloToMaster(){
        return "Hello World to Master";
    }
}

package com.example.oauth2client.controller;

import com.example.oauth2client.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContoller {
    private final HelloService helloService;

    public HelloContoller(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/welcome")
    public String getHome(){

        return helloService.getData();
    }
}

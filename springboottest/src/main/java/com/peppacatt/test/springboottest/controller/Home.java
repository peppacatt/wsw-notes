package com.peppacatt.test.springboottest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Home {
    @GetMapping("/home")
    public String home() {
        return String.format("hello world!!! The time now is: %s", new Date());
    }
}

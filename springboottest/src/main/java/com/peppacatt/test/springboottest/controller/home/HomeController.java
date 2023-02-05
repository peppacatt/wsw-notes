package com.peppacatt.test.springboottest.controller.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/hello")
    public String hello() {
        return String.format("hello world!!! The time now is: %s", new Date());
    }
}

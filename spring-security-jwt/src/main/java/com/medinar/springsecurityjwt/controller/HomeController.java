package com.medinar.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rommelmedina
 */
@RestController
public class HomeController {
    
    @GetMapping("/hello")
    public String home() {
        return "Hello World!";
    }
    

}

package com.medinar.springsecurityldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rommelmedina
 */
@RestController
public class HomeController {
    
    @GetMapping("/")
    public String index() {
        return "Home Page";
    }
}

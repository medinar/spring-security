package com.medinar.springsecurityjwt.controller;

import com.medinar.springsecurityjwt.model.AuthenticationRequest;
import com.medinar.springsecurityjwt.model.AuthenticationResponse;
import com.medinar.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rommelmedina
 */
@RestController
public class HomeController {

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String home() {
        return "Hello World!";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getUsername()
        );
        
        final String jwt = jwtUtil.generateToken(userDetails);
        
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

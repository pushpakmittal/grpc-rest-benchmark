package com.myapp.restserver.user.controller;

import com.myapp.restserver.user.model.UserDetails;
import com.myapp.restserver.user.service.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserDetailsController {

    final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/users")
    public UserDetails generateUserDetails() {
        return this.userDetailsService.generateUsers();
    }
}
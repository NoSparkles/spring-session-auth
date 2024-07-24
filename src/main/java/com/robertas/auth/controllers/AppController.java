package com.robertas.auth.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robertas.auth.models.Application;
import com.robertas.auth.models.MyUser;
import com.robertas.auth.services.AppService;
import com.robertas.auth.services.MyUserDetailsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {
    private final AppService service;
    private final MyUserDetailsService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Weolcome to the unprotected page";
    }

    @GetMapping("/protected")
    public String AuthorizedOnly() {
        return "protected";
    }

    @GetMapping("/all-apps")
    public List<Application> AllApplications() {
        return service.allApplications();
    }

    @GetMapping("/{id}")
    public Application applicationsById(@PathVariable Long id) {
        return service.getApplicationById(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser user) {
        userService.addUser(user);
        return user.getName();
    }

}

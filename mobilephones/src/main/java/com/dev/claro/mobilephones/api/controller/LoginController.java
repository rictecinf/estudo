package com.dev.claro.mobilephones.api.controller;

import com.dev.claro.mobilephones.domain.model.AuthenticationBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public AuthenticationBean basicAuth() {
        return new AuthenticationBean("You are authenticated");
    }

}

package com.example.fruithub.controller;


import com.example.fruithub.dto.LoginDto;
import com.example.fruithub.dto.SignUpDto;
import com.example.fruithub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody SignUpDto signUp){
        userService.register(signUp);
        return ResponseEntity.ok("Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.loginWithToken(loginDto));
    }

}

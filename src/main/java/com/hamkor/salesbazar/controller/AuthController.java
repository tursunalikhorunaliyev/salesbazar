package com.hamkor.salesbazar.controller;

import com.hamkor.salesbazar.dto.response.ResponseData;
import com.hamkor.salesbazar.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseData> register(@RequestParam("username") String username, @RequestParam("password") String password){
        return authService.registerUser(username, password);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestParam("username") String username, @RequestParam("password") String password){
        return authService.loginUser(username, password);
    }
}

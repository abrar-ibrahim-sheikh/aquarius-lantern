package com.scribblesphere.aquariuslantern.controller;

import com.scribblesphere.aquariuslantern.service.AuthService;
import com.scribblesphere.aquariuslantern.vo.AuthRequest;
import com.scribblesphere.aquariuslantern.vo.TokenData;
import com.scribblesphere.aquariuslantern.vo.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/add-user")
    public ResponseEntity<TokenData> addUser(@RequestBody UserData userData) {
        TokenData token = authService.addUser(userData);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenData> authenticate(@RequestBody AuthRequest authRequest) {
        TokenData token = authService.authenticate(authRequest);
       return ResponseEntity.ok(token);
    }
}

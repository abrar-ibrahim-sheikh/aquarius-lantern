//package com.scribblesphere.aquariuslantern.controller;
//
//import com.scribblesphere.aquariuslantern.dto.RegisterRequest;
//import com.scribblesphere.aquariuslantern.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<HttpStatus> signup(@RequestBody RegisterRequest registerRequest) {
//        authService.signup(registerRequest);
//        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<HttpStatus> login(@RequestBody LoginRequest loginRequest) {
////
////    }
//
//    @GetMapping("/welcome")
//    public String welcome() {
//        return "Welcome!";
//    }
//}

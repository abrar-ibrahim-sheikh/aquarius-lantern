//package com.scribblesphere.aquariuslantern.service.impl;
//
//import com.scribblesphere.aquariuslantern.dto.RegisterRequest;
//import com.scribblesphere.aquariuslantern.entity.User;
//import com.scribblesphere.aquariuslantern.repository.UserRepository;
//import com.scribblesphere.aquariuslantern.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void signup(RegisterRequest registerRequest) {
//        User user = new User();
//        user.setUsername(registerRequest.getUsername());
//        user.setPassword(encodePassword(registerRequest.getPassword()));
//        user.setEmail(registerRequest.getEmail());
//
//        userRepository.save(user);
//    }
//
//    private String encodePassword(String password) {
//        return passwordEncoder.encode(password);
//    }
//}

package com.scribblesphere.aquariuslantern.controller;

import com.scribblesphere.aquariuslantern.dto.UserData;
import com.scribblesphere.aquariuslantern.entity.User;
import com.scribblesphere.aquariuslantern.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserData>> getAllUsers() {
        List<UserData> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserData> getUserById(@PathVariable(name = "userId") Long userId) throws Exception {
        UserData user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserData> createUser(@Valid @RequestBody UserData user) {
        UserData newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED) ;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserData> updateUser(@PathVariable(name = "userId") Long userId, @RequestBody UserData user) {
        UserData updatedUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

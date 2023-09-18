package com.scribblesphere.aquariuslantern.service.impl;

import com.scribblesphere.aquariuslantern.entity.Role;
import com.scribblesphere.aquariuslantern.entity.User;
import com.scribblesphere.aquariuslantern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null)
            throw new UsernameNotFoundException("User with name" + username + " not found");

        Set<Role> roles = user.getRoles();
        String role = (roles.size() > 1) ? "ADMIN" : "USER";

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(role)
                .build();
    }
}

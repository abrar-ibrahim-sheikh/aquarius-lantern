package com.scribblesphere.aquariuslantern.service.impl;

import com.scribblesphere.aquariuslantern.dto.UserData;
import com.scribblesphere.aquariuslantern.entity.User;
import com.scribblesphere.aquariuslantern.exception.ResourceNotFoundException;
import com.scribblesphere.aquariuslantern.repository.UserRepository;
import com.scribblesphere.aquariuslantern.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserData> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(this::userToData)
                .collect(Collectors.toList());
    }

    @Override
    public UserData getUserById(Long userId) {
        UserData data = null;
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        if (user != null)
            data = userToData(user);
        return data;
    }

    @Override
    public UserData createUser(UserData user) {
        User newUser = dataToUser(user);
        return userToData(userRepository.save(newUser));
    }

    @Override
    public UserData updateUser(Long userId, UserData user) {
        User updatedUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setAbout(user.getAbout());
        return userToData(userRepository.save(updatedUser));
    }

    @Override
    public void deleteUser(Long userId) {
        if (userId != null)
            userRepository.deleteById(userId);
    }

    private User dataToUser(UserData data) {
        return modelMapper.map(data, User.class);
    }

    private UserData userToData(User user) {
        return  modelMapper.map(user, UserData.class);
    }

//    private PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}

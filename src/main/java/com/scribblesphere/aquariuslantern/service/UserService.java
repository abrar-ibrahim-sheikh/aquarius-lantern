package com.scribblesphere.aquariuslantern.service;

import com.scribblesphere.aquariuslantern.vo.UserData;

import java.util.List;

public interface UserService {

    List<UserData> getAllUsers();

    UserData getUserById(Long userId) throws Exception;

    UserData createUser(UserData user);

    UserData updateUser(Long userId, UserData user);

    void deleteUser(Long userId);
}

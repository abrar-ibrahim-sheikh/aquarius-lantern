package com.scribblesphere.aquariuslantern.service;

import com.scribblesphere.aquariuslantern.vo.AuthRequest;
import com.scribblesphere.aquariuslantern.vo.TokenData;
import com.scribblesphere.aquariuslantern.vo.UserData;

public interface AuthService {

    TokenData addUser(UserData userData);

    TokenData authenticate(AuthRequest authRequest);

}

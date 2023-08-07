package com.scribblesphere.aquariuslantern.service;

import com.scribblesphere.aquariuslantern.dto.RegisterRequest;

public interface AuthService {
    void signup(RegisterRequest registerRequest);

}

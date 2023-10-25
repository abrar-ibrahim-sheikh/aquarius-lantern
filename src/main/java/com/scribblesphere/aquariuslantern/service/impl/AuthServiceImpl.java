package com.scribblesphere.aquariuslantern.service.impl;

import com.scribblesphere.aquariuslantern.config.WebUserDetails;
import com.scribblesphere.aquariuslantern.entity.Role;
import com.scribblesphere.aquariuslantern.entity.User;
import com.scribblesphere.aquariuslantern.repository.UserRepository;
import com.scribblesphere.aquariuslantern.service.AuthService;
import com.scribblesphere.aquariuslantern.service.JwtService;
import com.scribblesphere.aquariuslantern.vo.AuthRequest;
import com.scribblesphere.aquariuslantern.vo.TokenData;
import com.scribblesphere.aquariuslantern.vo.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenData addUser(UserData userData) {
        User user = User.builder()
                .name(userData.getName())
                .email(userData.getEmail())
                .password(passwordEncoder.encode(userData.getPassword()))
                .role(Role.USER)
                .build();
//                .role(userData.getRole())
        userRepository.save(user);
        return generateToken(user);
    }

    @Override
    public TokenData authenticate(AuthRequest request) {
        final String email = request.getUsername();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, request.getPassword())
        );
        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("User not found for email: " + email);
        }
        var user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found for email: " + email));
        return generateToken(user);
    }

    private TokenData generateToken(User user) {
        WebUserDetails webUser = WebUserDetails.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        return jwtService.generateToken(webUser);
    }
}

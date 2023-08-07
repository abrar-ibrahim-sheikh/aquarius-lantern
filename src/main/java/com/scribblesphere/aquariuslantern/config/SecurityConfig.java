//package com.scribblesphere.aquariuslantern.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
////        UserDetails admin = User
////                .withUsername("Admin")
////                .password(passwordEncoder().encode("root"))
////                .roles("ADMIN")
////                .build();
////        UserDetails user = User
////                .withUsername("Client")
////                .password(passwordEncoder().encode("password"))
////                .roles("USER")
////                .build();
////        return new InMemoryUserDetailsManager(admin, user);
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean
//    @Order(1)
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/**")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .formLogin(withDefaults());
//        return http.build();
//    }
//
////    @Bean
////    @Order(2)
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
////                .httpBasic(withDefaults());
////        return http.build();
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}

package ru.zainab.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.POST, "/users")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/users")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/users/{id}")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/users/{id}")
                .hasAnyRole("USER", "ADMIN")

                .requestMatchers(HttpMethod.PUT, "/users/{id}")
                .hasAnyRole("USER", "ADMIN")

                .requestMatchers(HttpMethod.PUT, "/users/password")
                .hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated());


        return http.build();
    }

}

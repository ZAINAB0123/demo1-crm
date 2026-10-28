package ru.zainab.authservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.zainab.authservice.dto.LoginRequest;
import ru.zainab.authservice.dto.LoginResponse;
import ru.zainab.authservice.secret.JwtService;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                );
        Authentication authentication = authenticationManager.authenticate(token);
        String accessToken = jwtService.generateAccessToken(authentication);
        return new LoginResponse(
                accessToken, jwtService.getAccessTokenExpirationSeconds());

    }
}

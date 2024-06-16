package at.technikum.springrestbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import at.technikum.springrestbackend.dto.login.LoginRequest;
import at.technikum.springrestbackend.dto.login.TokenResponse;
import at.technikum.springrestbackend.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.attemptLogin(loginRequest);
    }
}

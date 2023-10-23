package com.quillmates.quillmatesbackend.controllers;


import com.quillmates.quillmatesbackend.dtos.AuthenticationResponseDto;
import com.quillmates.quillmatesbackend.dtos.LoginRequestDto;
import com.quillmates.quillmatesbackend.dtos.RegistrationRequestDto;
import com.quillmates.quillmatesbackend.services.abstractions.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDto requestDto) {
        var authReponse = authService.register(requestDto);

        return new ResponseEntity<>(authReponse, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto) {
        var authResponse = authService.login(requestDto);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}

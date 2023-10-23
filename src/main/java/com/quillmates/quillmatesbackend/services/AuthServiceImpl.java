package com.quillmates.quillmatesbackend.services;

import com.quillmates.quillmatesbackend.dtos.AuthenticationResponseDto;
import com.quillmates.quillmatesbackend.dtos.LoginRequestDto;
import com.quillmates.quillmatesbackend.dtos.RegistrationRequestDto;
import com.quillmates.quillmatesbackend.exceptions.InvalidCredentialException;
import com.quillmates.quillmatesbackend.repositories.UserRepository;
import com.quillmates.quillmatesbackend.security.JwtService;
import com.quillmates.quillmatesbackend.services.abstractions.AuthService;
import com.quillmates.quillmatesbackend.services.validators.EmailValidatorService;
import com.quillmates.quillmatesbackend.services.validators.PasswordValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final EmailValidatorService emailValidatorService;
    private final PasswordValidatorService passwordValidatorService;
    private final UserService userService;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponseDto register(RegistrationRequestDto requestDto) {
        boolean isValidEmail = emailValidatorService.test(requestDto.getEmail());
        if (!isValidEmail) {
            throw new InvalidCredentialException("email not valid");
        }

        boolean isValidPassword = passwordValidatorService.test(requestDto.getPassword());
        if (!isValidPassword) {
            throw new InvalidCredentialException("password not valid, ensure at least one lowercase letter, uppercase letter and one digit, at least 8 characters and one special character");
        }

        if (requestDto.getUsername() == null || requestDto.getUsername().isEmpty()) {
            throw new InvalidCredentialException("Username not valid");
        }
        userService.signUpUser(requestDto);

        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                requestDto.getEmail(),
                requestDto.getPassword()
        ));

        var jwtToken = jwtService.generateToken(requestDto.getEmail());

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto login(LoginRequestDto requestDto) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                requestDto.getEmail(),
                requestDto.getPassword()
        ));

        var user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email not found."));
        var jwtToken = jwtService.generateToken(user.getEmail());

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}

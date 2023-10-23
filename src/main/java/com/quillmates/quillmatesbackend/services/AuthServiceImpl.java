package com.quillmates.quillmatesbackend.services;

import com.quillmates.quillmatesbackend.dtos.RegistrationRequestDto;
import com.quillmates.quillmatesbackend.exceptions.InvalidCredentialException;
import com.quillmates.quillmatesbackend.services.abstractions.AuthService;
import com.quillmates.quillmatesbackend.services.validators.EmailValidatorService;
import com.quillmates.quillmatesbackend.services.validators.PasswordValidatorService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final EmailValidatorService emailValidatorService;
    private final PasswordValidatorService passwordValidatorService;
    private final UserService userService;
    @Override
    public void register(RegistrationRequestDto requestDto) {

        boolean isValidEmail = emailValidatorService.test(requestDto.getEmail());
        if (!isValidEmail) {
            throw new InvalidCredentialException("email not valid");
        }
        boolean isValidPassword = passwordValidatorService.test(requestDto.getPassword());
        if(!isValidPassword) {
            throw new InvalidCredentialException("password not valid, ensure at least one lowercase letter, uppercase letter and one digit, at least 8 characters");
        }

        if (requestDto.getUsername() == null || requestDto.getUsername().isEmpty()) {
            throw new InvalidCredentialException("Username not valid");
        }
        userService.signUpUser(requestDto);
    }
}

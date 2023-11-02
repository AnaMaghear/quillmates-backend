package com.quillmates.quillmatesbackend.services.abstractions;


import com.quillmates.quillmatesbackend.dtos.AuthenticationResponseDto;
import com.quillmates.quillmatesbackend.dtos.LoginRequestDto;
import com.quillmates.quillmatesbackend.dtos.RegistrationRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthenticationResponseDto register(RegistrationRequestDto requestDto);

    AuthenticationResponseDto login(LoginRequestDto requestDto);
}

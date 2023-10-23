package com.quillmates.quillmatesbackend.services.abstractions;


import com.quillmates.quillmatesbackend.dtos.RegistrationRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    void register(RegistrationRequestDto requestDto);
}

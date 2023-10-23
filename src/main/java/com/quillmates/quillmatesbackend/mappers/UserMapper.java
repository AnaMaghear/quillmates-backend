package com.quillmates.quillmatesbackend.mappers;

import com.quillmates.quillmatesbackend.dtos.RegistrationRequestDto;
import com.quillmates.quillmatesbackend.models.User;
import org.springframework.stereotype.Service;


@Service
public class UserMapper {
    public User registrationRequestDtotoUser(RegistrationRequestDto requestDto) {
        return User.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .build();
    }
}

package com.quillmates.quillmatesbackend.services;


import com.quillmates.quillmatesbackend.dtos.RegistrationRequestDto;
import com.quillmates.quillmatesbackend.exceptions.UserAlreadyExistsException;
import com.quillmates.quillmatesbackend.mappers.UserMapper;
import com.quillmates.quillmatesbackend.models.User;
import com.quillmates.quillmatesbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void signUpUser(RegistrationRequestDto userDto) throws UserAlreadyExistsException {
        User user = userMapper.registrationRequestDtotoUser(userDto);

        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserAlreadyExistsException("User already exists");
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.saveAndFlush(user);
    }


}

package com.yosra.jwt.backend.services;

import com.yosra.jwt.backend.dtos.CredentialsDto;
import com.yosra.jwt.backend.dtos.SignUpDto;
import com.yosra.jwt.backend.dtos.UserDto;
import com.yosra.jwt.backend.entites.User;
import com.yosra.jwt.backend.exceptions.AppException;
import com.yosra.jwt.backend.mappers.UserMapper;
import com.yosra.jwt.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    // Login logic
    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user); // Return UserDto without token, it will be set in controller
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    // Registration logic
    public UserDto register(SignUpDto signUpDto) {
        Optional<User> optionalUser = userRepository.findByLogin(signUpDto.login());
        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser); // Return UserDto without token, it will be set in controller
    }

    // Find user by login
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
}

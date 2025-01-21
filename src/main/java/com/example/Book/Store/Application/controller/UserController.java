package com.example.Book.Store.Application.controller;

import com.example.Book.Store.Application.entity.User;
import com.example.Book.Store.Application.requestdto.*;
import com.example.Book.Store.Application.responsedto.LoginResponse;
import com.example.Book.Store.Application.responsedto.UserResponseDto;
import com.example.Book.Store.Application.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        UserResponseDto userResponse =  userService.registerUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse=userService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @GetMapping("/admin/{userId}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable long userId) {
        UserResponseDto userResponse =  userService.findUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> userResponses =  userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }

    @PutMapping("/admin/{userId}")
    public ResponseEntity<UserResponseDto> updateUserById(@PathVariable long userId, @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponse = userService.updateUserById(userId, userRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
    }
    @DeleteMapping("/admin/{userId}")
    public ResponseEntity<UserResponseDto> deleteUserById(@PathVariable long userId) {
        UserResponseDto userResponse = userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        userService.initiateForgetPassword(forgetPasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body("OTP has been sent to your registered email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        UserResponseDto userResponse = userService.resetPassword(resetPasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Your Password reset Successfully");
    }

}

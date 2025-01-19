package com.example.Book.Store.Application.service;

import com.example.Book.Store.Application.requestdto.*;
import com.example.Book.Store.Application.responsedto.LoginResponse;
import com.example.Book.Store.Application.responsedto.UserResponseDto;

import java.util.List;

public interface UserService {
     UserResponseDto registerUser(RegistrationRequest registrationRequest);

     LoginResponse login(LoginRequest loginRequest);

     UserResponseDto findUserById(long userId);

     List<UserResponseDto> findAllUsers();

     UserResponseDto updateUserById(long userId, UserRequestDto userRequest);

    UserResponseDto deleteUserById(long userId);

    void initiateForgetPassword(ForgetPasswordRequest forgetPasswordRequest);

    UserResponseDto resetPassword(ResetPasswordRequest resetPasswordRequest);
}

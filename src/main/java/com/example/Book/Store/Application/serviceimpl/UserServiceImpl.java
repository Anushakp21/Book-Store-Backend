package com.example.Book.Store.Application.serviceimpl;

import com.example.Book.Store.Application.Handler.InvalidResetTokenException;
import com.example.Book.Store.Application.Handler.UserNotFoundByIdException;
import com.example.Book.Store.Application.entity.User;
import com.example.Book.Store.Application.mapper.UserMapper;
import com.example.Book.Store.Application.repository.UserRepository;
import com.example.Book.Store.Application.requestdto.*;
import com.example.Book.Store.Application.responsedto.LoginResponse;
import com.example.Book.Store.Application.responsedto.UserResponseDto;
import com.example.Book.Store.Application.security.OtpUtil;
import com.example.Book.Store.Application.security.Util;
import com.example.Book.Store.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final Util util;
    private final EmailService emailService;
    private final BCryptPasswordEncoder encoder;
    private final OtpUtil otpUtil;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AuthenticationManager authenticationManager, Util util, EmailService emailService, BCryptPasswordEncoder encoder, OtpUtil otpUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.util = util;
        this.emailService = emailService;
        this.encoder = encoder;
        this.otpUtil = otpUtil;
    }

    @Override
    public UserResponseDto registerUser(RegistrationRequest registrationRequest) {
        User user = userMapper.mapToUser(registrationRequest);
        user = userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail())
        );

        String token = util.createToken(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(user.getEmail());
        loginResponse.setJwtToken(token);
        loginResponse.setUserId(user.getUserId());
        loginResponse.setRole(user.getRole());
        return loginResponse;
    }

    @Override
    public UserResponseDto findUserById(long userId) {
        return userRepository.findById(userId)
                .map(user -> userMapper.mapToUserResponse(user))
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user"));
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userMapper.mapToUserResponse(user)).toList();
    }

    @Override
    public UserResponseDto updateUserById(long userId, UserRequestDto userRequest)
    {
        return userRepository.findById(userId)
                .map(user -> {
                    user = userMapper.mapToUser(userRequest, user);
                    user.setUpdatedDate(LocalDate.now());
                    userRepository.save(user);
                    return userMapper.mapToUserResponse(user);
                }).orElseThrow(() -> new UserNotFoundByIdException("Failed to update user"));
    }

    @Override
    public UserResponseDto deleteUserById(long userId) {
        return userRepository.findById(userId)
                .map(user ->
                {
                    userRepository.deleteById(userId);
                    return userMapper.mapToUserResponse(user);
                }
                ).orElseThrow(() -> new UserNotFoundByIdException("Failed to update user"));
    }

    @Override
    public void initiateForgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(forgetPasswordRequest.getEmail());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundByIdException("User with this email does not exist.");
        }

        User user = optionalUser.get();

        //generate otp
        String otp=otpUtil.generateOtp();

        user.setSetResetToken(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5)); // OTP valid for 5 minutes
        userRepository.save(user);

        // Send Email with the OTP or Reset Link
        String subject = "Password Reset Request";
        String message = "Your OTP for resetting your password is: " + otp +
                "\nThis OTP is valid for the next 5 minutes.";
        emailService.sendEmail(user.getEmail(), subject, message);
    }

    @Override
    public UserResponseDto resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Optional<User> userOptional = userRepository.findBySetResetToken(resetPasswordRequest.getOtp());

        if (userOptional.isEmpty()) {
            throw new InvalidResetTokenException("Invalid reset token.");
        }

        User user = userOptional.get();

        // Check if OTP is expired
        if (user.getOtpExpiry() == null || user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new InvalidResetTokenException("OTP has expired.");
        }

        // Check if the provided OTP matches
        if (!user.getSetResetToken().equals(resetPasswordRequest.getOtp())) {
            throw new InvalidResetTokenException("Invalid OTP.");
        }

        // Generate OTP based on the same logic (no need to store it)
        String expectedOtp = otpUtil.generateOtp();


        // Update the user's password
        String encodedPassword = encoder.encode(resetPasswordRequest.getNewPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return new UserResponseDto();
    }
}

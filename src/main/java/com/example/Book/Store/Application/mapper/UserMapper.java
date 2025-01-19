package com.example.Book.Store.Application.mapper;

import com.example.Book.Store.Application.entity.User;
import com.example.Book.Store.Application.requestdto.RegistrationRequest;
import com.example.Book.Store.Application.requestdto.UserRequestDto;
import com.example.Book.Store.Application.responsedto.UserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class UserMapper {

    @Autowired
    private PasswordEncoder encoder;

    public User mapToUser(UserRequestDto userRequestDto,User user)
    {
       user.setfName(userRequestDto.getfName());
       user.setlName(userRequestDto.getlName());
       user.setDob(userRequestDto.getDob());
       user.setRole(userRequestDto.getRole());
       return user;
    }

    public User mapToUser(RegistrationRequest registrationRequest)
    {
      User user=new User();
      user.setfName(registrationRequest.getfName());
      user.setlName(registrationRequest.getlName());
      user.setEmail(registrationRequest.getEmail());
      user.setDob(registrationRequest.getDob());
      user.setRole(registrationRequest.getRole());
      user.setPassword(encoder.encode(registrationRequest.getPassword()));
      user.setRegisteredDate(LocalDate.now());
      return user;
    }

    public UserResponseDto mapToUserResponse(User user)
    {
       UserResponseDto userResponseDto=new UserResponseDto();
       userResponseDto.setUserId(user.getUserId());
       userResponseDto.setfName(user.getfName());
       userResponseDto.setlName(user.getlName());
       userResponseDto.setEmail(user.getEmail());
       userResponseDto.setDob(user.getDob());
       userResponseDto.setRole(user.getRole());
       userResponseDto.setRegisteredDate(user.getRegisteredDate());
       userResponseDto.setUpdatedDate(user.getUpdatedDate());
       return userResponseDto;
    }
}

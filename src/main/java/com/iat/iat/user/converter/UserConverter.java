package com.iat.iat.user.converter;

import com.iat.iat.user.dto.UserDto;
import com.iat.iat.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Autowired CategoryConverter categoryConverter;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserDto entityToDto(User user){
        UserDto userDto =new UserDto();
        userDto.setId(user.getId());
        userDto.setContact(user.getContact());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public User dtoToEntity(UserDto userDto){
        User user = new User();
        user.setContact(userDto.getContact());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassWord()));
        return user;
    }

    public List<UserDto> entityToDto(List<User> users){
        return users.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<User> dtoToEntity(List<UserDto> userDtos) {
        return userDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}

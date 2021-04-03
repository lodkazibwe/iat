package com.iat.iat.user.rest.v1;

import com.iat.iat.security.AuthResponse;
import com.iat.iat.security.MyUserDetailsService;
import com.iat.iat.user.converter.UserConverter;
import com.iat.iat.user.dto.UserDto;
import com.iat.iat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired UserService userService;
    @Autowired UserConverter userConverter;
    @Autowired MyUserDetailsService myUserDetailsService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> signUp(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);

    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDto> currentUser(){
        return new ResponseEntity<>(userConverter.entityToDto(myUserDetailsService.currentUser()), HttpStatus.OK);
    }



}

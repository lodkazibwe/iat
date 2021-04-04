package com.iat.iat.security;

import com.iat.iat.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseV2 {
    private final String jwt;
    private UserDto userDto;
}

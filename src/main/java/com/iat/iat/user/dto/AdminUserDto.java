package com.iat.iat.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDto extends UserDto{
    @NotNull(message ="tittle cannot be null")
    private String tittle;
}

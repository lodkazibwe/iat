package com.iat.iat.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    @NotNull(message ="contact cannot be null")
    private String contact;
    @NotNull(message ="email cannot be null")
    private String email;
    @NotNull(message ="name cannot be null")
    private String name;
    @NotNull(message ="passWord cannot be null")
    private String passWord;


}

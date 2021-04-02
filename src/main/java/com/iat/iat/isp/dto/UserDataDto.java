package com.iat.iat.isp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {
    private int id;
    @NotNull(message ="firstName cannot be null")
    private String firstName;
    @NotNull(message ="lastName cannot be null")
    private String lastName;
    @NotNull(message ="contact cannot be null")
    private String contact;
    @NotNull(message ="nin cannot be null")
    private String nin;
    @NotNull(message ="email cannot be null")
    private String email;
    @NotNull(message ="residence cannot be null")
    private String residence;
    @NotNull(message ="job cannot be null")
    private String job;
}

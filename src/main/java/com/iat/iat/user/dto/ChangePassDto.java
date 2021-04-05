package com.iat.iat.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassDto {
    private String oldPass;
    private String newPass;
    private String confirm;

}

package com.iat.iat.flutterWave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String email;
    private String phoneNumber;
    private String name;
}

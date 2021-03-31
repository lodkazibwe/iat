package com.iat.iat.isp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ISPDto {
    private int id;
    @NotNull(message ="name can not be null")
    private String name;
    @NotNull (message ="url cannot be null")
    private String url;
}
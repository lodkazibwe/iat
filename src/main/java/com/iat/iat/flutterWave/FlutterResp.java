package com.iat.iat.flutterWave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlutterResp {
    private String status;
    private String message;
    private RespData  data;
}

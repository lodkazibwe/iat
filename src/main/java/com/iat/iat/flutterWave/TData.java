package com.iat.iat.flutterWave;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TData {
    private int id;
    private double amount;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date created_at;
}

package com.iat.iat.flutterWave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyData {
    private int id;
    private int tx_ref;
    private String flw_ref;
    private double amount;
    private String currency;
    private String payment_type;
}

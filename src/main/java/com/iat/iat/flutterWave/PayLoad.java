package com.iat.iat.flutterWave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayLoad {
    private int tx_ref;
    private double amount;
    private String currency;
    private String redirect_url;
    private String paymentOptions;
    private Meta meta;
    private Customer customer;
    private Customizations customizations;

}

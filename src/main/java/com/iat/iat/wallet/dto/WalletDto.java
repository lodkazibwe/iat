package com.iat.iat.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {
    private int id;
    @NotNull(message ="uid cannot be null")
    private int uid;
    @NotNull (message ="balance cannot be null")
    private double balance;
    private int lastPayment;
    private String contact;
}

package com.iat.iat.payment.dto;

import com.iat.iat.payment.model.IatPackage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyIatDto {
    private int isp;
    private IatPackage iatPackage;
    private String contact;
}

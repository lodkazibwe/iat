package com.iat.iat.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iat.iat.payment.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositHistory {
    @Id
    @GeneratedValue
    private int id;
    private PaymentMethod paymentMethod;
    private double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", locale = "pt-BR", timezone = "EAT")
    @Temporal(TemporalType.DATE)
    private Date date;
}

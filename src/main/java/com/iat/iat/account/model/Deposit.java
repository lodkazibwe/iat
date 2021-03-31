package com.iat.iat.account.model;

import com.iat.iat.payment.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Deposit {
    @Id
    @GeneratedValue
    private int id;
    private PaymentMethod paymentMethod;
    private double amount;
}

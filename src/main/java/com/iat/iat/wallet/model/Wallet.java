package com.iat.iat.wallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @GeneratedValue
    @Id
    private int id;
    private int uid;
    private String contact;
    private double balance;
    private int lastPayment;
}

package com.iat.iat.account.model;

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
public class Iat {
    @Id
    @GeneratedValue
    private int id;
    private int isp;
    private String ispName;
    private double amount;
}

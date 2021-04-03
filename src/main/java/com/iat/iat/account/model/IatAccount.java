package com.iat.iat.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IatAccount {
    @Id
    @GeneratedValue
    private int id;
    private String contact;
    private int lastTransaction;
    private Date expireAt;
}

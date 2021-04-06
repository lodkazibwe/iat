package com.iat.iat.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireAt;
}

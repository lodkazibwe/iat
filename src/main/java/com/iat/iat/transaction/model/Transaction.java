package com.iat.iat.transaction.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iat.iat.isp.model.ISP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private String paidBy;
    private String paidFor;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", locale = "pt-BR", timezone = "EAT")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "EAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    private int isp;

}

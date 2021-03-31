package com.iat.iat.payment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private int id;
    private int externalId;
    private int walletId;
    private double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String paymentType;
    private String message;
    private String ref;
    private String status;
}

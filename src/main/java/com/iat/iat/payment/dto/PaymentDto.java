package com.iat.iat.payment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iat.iat.payment.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private int id;
    private String externalId;
    @NotNull (message ="amount cannot be null")
    private double amount;
    private int walletId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", locale = "pt-BR", timezone = "EAT")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,locale = "pt-BR", timezone = "EAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    private PaymentMethod paymentMethod;
    private String paymentType;
    private String message;
    private String ref;
    private String status;
}

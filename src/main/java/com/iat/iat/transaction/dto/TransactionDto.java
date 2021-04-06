package com.iat.iat.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iat.iat.isp.dto.ISPDto;
import com.iat.iat.transaction.model.TransactionStatus;
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
public class TransactionDto {
    private int id;
    @NotNull(message ="user cannot be null")
    private int userId;
    @NotNull (message ="isp cannot be null")
    private int isp;
    @NotNull (message ="paidBy cannot be null")
    private String paidBy;
    @NotNull (message ="paidFor cannot be null")
    private String paidFor;
    @NotNull (message ="amount cannot be null")
    private double amount;
    @NotNull (message ="status cannot be null")
    private TransactionStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
}

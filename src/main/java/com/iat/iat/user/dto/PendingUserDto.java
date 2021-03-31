package com.iat.iat.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iat.iat.user.model.CodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PendingUserDto {
    private int id;
    private String number;
    private String token;
    private CodeStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
}

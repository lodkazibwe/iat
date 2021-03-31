package com.iat.iat.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PendingUser {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String number;
    private String token;
    @Enumerated(EnumType.STRING)
    private CodeStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

}

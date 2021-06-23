package com.iat.iat.metaData.model;

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
public class DeviceMetaData {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private String contact;
    private String deviceDetails;
    private String clientIp;
    private String location;
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "GMT+1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoggedIn;
}

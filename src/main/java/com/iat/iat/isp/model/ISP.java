package com.iat.iat.isp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ISP {
    @GeneratedValue
    @Id
    private int id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String url;
}

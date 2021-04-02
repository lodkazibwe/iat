package com.iat.iat.isp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserData {
    @GeneratedValue
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String contact;
    private String nin;
    private String email;
    private String residence;
    private String job;

}

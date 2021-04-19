package com.iat.iat.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String contact;
    private String email;
    private String name;
    private String password;
    private String image;
    @OneToMany(targetEntity = Role.class,cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn()
    private Set<Role> roles;

}

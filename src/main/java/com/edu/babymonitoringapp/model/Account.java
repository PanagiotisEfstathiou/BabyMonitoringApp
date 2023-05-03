package com.edu.babymonitoringapp.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "account")
@Data

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique=true)
    private String username;

    private String password;
    private Role role;
}

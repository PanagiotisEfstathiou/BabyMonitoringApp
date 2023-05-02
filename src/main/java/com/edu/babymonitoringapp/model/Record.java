package com.edu.babymonitoringapp.model;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import lombok.Data;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "record")
@Data
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private double duration;
    private double ml;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}

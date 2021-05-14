package com.project.consultant.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
public class Consulttime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int consultantid;
    private Date date;
    private Time fromtime;
    private int hours;
}

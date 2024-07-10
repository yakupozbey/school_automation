package com.education.schoolautomation.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends Identity{
    private String ssn;
    private double salary;





    //@OneToOne
    //private School school;
}

package com.education.schoolautomation.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass//Veri tabanı tablosu olmayan üst sınıfı belirtir
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID identityId;
    protected String fullName;
    protected String tckn;
    protected String age;
    protected String phoneNumber;
    protected String address;
}

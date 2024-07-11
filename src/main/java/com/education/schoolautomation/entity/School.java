package com.education.schoolautomation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID schoolId;
    private String schoolType;
    private String schoolName;
    private String schoolAddress;


    @OneToOne(cascade = CascadeType.ALL) //mappedBy school eşliyor.Üzerine tıkla göreceksin.
    @JoinColumn(name = "manager_id", referencedColumnName = "identityId")
    private Manager manager;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "school-assistant-managers")
    private List<AssistantManager> assistantManagers;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "school-class-rooms")
    private List<ClassRoom> classRooms;
}

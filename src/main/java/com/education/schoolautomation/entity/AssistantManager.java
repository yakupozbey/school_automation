package com.education.schoolautomation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssistantManager extends Identity{
    private String ssn;
    private double salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    @JsonBackReference(value = "school-assistant-managers")
    private School school;
}

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
public class Student extends Identity{
    private String studentNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_id")
    @JsonBackReference(value = "lesson-students")
    private Lesson lesson;
}

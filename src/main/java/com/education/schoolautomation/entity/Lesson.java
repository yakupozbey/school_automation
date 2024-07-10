package com.education.schoolautomation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID lessonId;
    private String lessonName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    @JsonBackReference(value = "branch-lessons")
    private Branch branch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher lessonTeacher;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)  //, fetch = FetchType.LAZY
    @JsonManagedReference(value = "lesson-students")
    private List<Student> students;
}

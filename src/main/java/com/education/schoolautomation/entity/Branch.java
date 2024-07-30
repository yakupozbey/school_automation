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
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID branchId;
    private String branchName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_room_id")
    @JsonBackReference(value = "class-room-branches")
    private ClassRoom classRoom;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher classTeacher;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "branch-lessons")
    private List<Lesson> lessons;
}

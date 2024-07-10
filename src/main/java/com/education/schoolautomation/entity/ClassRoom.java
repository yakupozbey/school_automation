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
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID classRoomId;
    private String classRoomName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    @JsonBackReference(value = "school-class-rooms")
    private School school;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL) //, fetch = FetchType.LAZY
    @JsonManagedReference(value = "class-room-branches")
    private List<Branch> branches;
}

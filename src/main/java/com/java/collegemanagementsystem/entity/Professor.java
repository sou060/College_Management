package com.java.collegemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Subject> subjects;

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.setProfessor(this);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.setProfessor(null);
    }

    @ManyToMany(mappedBy = "professors", fetch = FetchType.LAZY)
    private List<Student> students;


}

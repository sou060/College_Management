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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.getStudents().add(this);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.getStudents().remove(this);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_professor", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> professors;

    public void addProfessor(Professor professor) {
        professors.add(professor);
        professor.getStudents().add(this);
    }

    public void removeProfessor(Professor professor) {
        professors.remove(professor);
        professor.getStudents().remove(this);
    }

    @OneToOne(mappedBy = "student")
    private AdmissionRecord admissionRecord;
}

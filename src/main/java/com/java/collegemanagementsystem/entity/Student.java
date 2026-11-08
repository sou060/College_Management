package com.java.collegemanagementsystem.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_professor", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> professors = new ArrayList<>();
    @OneToOne(mappedBy = "student")
    private AdmissionRecord admissionRecord;

    public Student() {}

    public Student(Long id, String name, List<Subject> subjects, List<Professor> professors, AdmissionRecord admissionRecord) {
        this.id = id;
        this.name = name;
        this.subjects = subjects == null ? new ArrayList<>() : subjects;
        this.professors = professors == null ? new ArrayList<>() : professors;
        this.admissionRecord = admissionRecord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public AdmissionRecord getAdmissionRecord() {
        return admissionRecord;
    }

    public void setAdmissionRecord(AdmissionRecord admissionRecord) {
        this.admissionRecord = admissionRecord;
    }
}

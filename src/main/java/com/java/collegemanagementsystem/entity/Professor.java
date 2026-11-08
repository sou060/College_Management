package com.java.collegemanagementsystem.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String title;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Subject> subjects = new ArrayList<>();
    @ManyToMany(mappedBy = "professors", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    public Professor() {}

    public Professor(Long id, String title, List<Subject> subjects, List<Student> students) {
        this.id = id;
        this.title = title;
        this.subjects = subjects == null ? new ArrayList<>() : subjects;
        this.students = students == null ? new ArrayList<>() : students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}

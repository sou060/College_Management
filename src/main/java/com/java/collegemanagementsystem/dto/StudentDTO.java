package com.java.collegemanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class StudentDTO {
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(max = 120, message = "Name must be at most 120 characters")
    private String name;
    
    private List<Long> subjectIds;
    private List<Long> professorIds;
    private Long admissionRecordId;

    public StudentDTO() {}

    public StudentDTO(Long id, String name, List<Long> subjectIds, List<Long> professorIds, Long admissionRecordId) {
        this.id = id;
        this.name = name;
        this.subjectIds = subjectIds;
        this.professorIds = professorIds;
        this.admissionRecordId = admissionRecordId;
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

    public List<Long> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(List<Long> subjectIds) {
        this.subjectIds = subjectIds;
    }

    public List<Long> getProfessorIds() {
        return professorIds;
    }

    public void setProfessorIds(List<Long> professorIds) {
        this.professorIds = professorIds;
    }

    public Long getAdmissionRecordId() {
        return admissionRecordId;
    }

    public void setAdmissionRecordId(Long admissionRecordId) {
        this.admissionRecordId = admissionRecordId;
    }
}

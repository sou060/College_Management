package com.java.collegemanagementsystem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class AdmissionRecordDTO {
    private Long id;

    @NotNull(message = "Fees are required")
    @PositiveOrZero(message = "Fees cannot be negative")
    private Integer fees;

    @NotNull(message = "Student ID is required")
    @Positive(message = "Student ID must be positive")
    private Long studentId;

    public AdmissionRecordDTO() {}

    public AdmissionRecordDTO(Long id, Integer fees, Long studentId) {
        this.id = id;
        this.fees = fees;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}

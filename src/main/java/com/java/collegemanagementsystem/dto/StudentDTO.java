package com.java.collegemanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private List<Long> subjectIds;
    private List<Long> professorIds;
    private Long admissionRecordId;
}

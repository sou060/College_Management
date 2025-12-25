package com.java.collegemanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private Long professorId;
    private List<Long> studentIds;
}

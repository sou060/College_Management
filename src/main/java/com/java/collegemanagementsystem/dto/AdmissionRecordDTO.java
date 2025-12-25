package com.java.collegemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRecordDTO {
    private Long id;
    private Integer fees;
    private Long studentId;
}

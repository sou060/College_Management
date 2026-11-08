package com.java.collegemanagementsystem.controller;

import com.java.collegemanagementsystem.dto.AdmissionRecordDTO;
import com.java.collegemanagementsystem.service.AdmissionRecordService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@RestController
@Validated
@RequestMapping({"/api/v1/admission", "/api/admission"})
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @PostMapping
    public ResponseEntity<AdmissionRecordDTO> createAdmissionRecord(
            @Valid @RequestBody AdmissionRecordDTO admissionRecordDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(admissionRecordService.createAdmission(admissionRecordDTO));
    }

    @GetMapping
    public java.util.List<AdmissionRecordDTO> getAllAdmissionRecords() {
        return admissionRecordService.getAllAdmissionRecords();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmissionRecord(@PathVariable @Positive Long id) {
        admissionRecordService.deleteAdmission(id);
        return ResponseEntity.status(HttpStatus.OK).body("Admission record deleted successfully");
    }

}

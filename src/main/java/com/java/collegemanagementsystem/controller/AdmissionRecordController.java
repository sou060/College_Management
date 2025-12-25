package com.java.collegemanagementsystem.controller;

import com.java.collegemanagementsystem.entity.AdmissionRecord;
import com.java.collegemanagementsystem.service.AdmissionRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admission")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @PostMapping
    public ResponseEntity<AdmissionRecord> createAdmissionRecord(@RequestBody AdmissionRecord admissionRecord) {
        return ResponseEntity.status(HttpStatus.CREATED).body(admissionRecordService.createAdmission(admissionRecord));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmissionRecord(@PathVariable Long id) {
        admissionRecordService.deleteAdmission(id);
        return ResponseEntity.status(HttpStatus.OK).body("Admission record deleted successfully");
    }

}
